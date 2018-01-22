package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.*;
import cn.haizhi.market.main.bean.qiyuan.User;
import cn.haizhi.market.main.mapper.madao.*;
import cn.haizhi.market.main.mapper.qiyuan.UserMapper;
import cn.haizhi.market.other.enums.madao.*;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.DateFormatUtil;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.Group;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    PgGroupMapper groupMapper;

    @Autowired
    GroupInfoMapper groupInfoMapper;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    PgOrderMasterMapper pgOrderMasterMapper;

    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Autowired
    UserMapper userMapper;


    //发起拼购组，组限制人数要从group_info表中查到
    public PgGroup createGroup(Long userId, String orderId){
        PgOrderMaster pgOrderMaster = pgOrderMasterMapper.selectByPrimaryKey(orderId);
        if(pgOrderMaster==null)
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(orderId));
        if(!pgOrderMaster.getUserId().equals(userId))
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR, IdResultMap.getIdMap(orderId));
        if(!pgOrderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_PAY_STATUS_ERROR, IdResultMap.getIdMap(orderId));

        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(DateFormatUtil.DateToString(new Date()));
        if(groupInfo==null || groupInfo.getGroupNum()<=0)
            throw new ResultException(ErrorEnum.GROUP_INFO_ERROR.getMessage());
        PgGroup group = new PgGroup();
        group.setGroupId(KeyUtil.genUniquKey());
        group.setGroupNum(groupInfo.getGroupNum());
        group.setGroupCount(1);
        group.setDeadDate(DateFormatUtil.getLastTimeOfDay());
        Date date = group.getDeadDate();
        GroupMember groupMember = addGroupMember(pgOrderMaster, group);
        group.setLeadMemberId(groupMember.getMemberId());
        pgOrderMaster.setGroupId(group.getGroupId());
        pgOrderMaster.setOrderStatus(PgOrderEnum.IN_GROUP.getCode());
        pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        groupMapper.insert(group);
        return group;
    }

    //根据状态 获取拼购组列表
    public List<GroupDTO> getGroupList(Byte groupStatus, Byte activeStatus){
        List<GroupDTO> groupDTOList = commonMapper.getGroupDTOByExample(groupStatus, activeStatus);
        return groupDTOList;
    }

    public GroupDTO getGroupByOne(Long userId, String orderId){
        PgOrderMaster pgOrderMaster = pgOrderMasterMapper.selectByPrimaryKey(orderId);
        if(pgOrderMaster==null){
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(orderId));
        }
        if(!userId.equals(pgOrderMaster.getUserId())){
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        }

        String groupId = pgOrderMaster.getGroupId();
        PgGroupExample example = new PgGroupExample();
        PgGroupExample.Criteria criteria = example.createCriteria();
        PgGroup group = groupMapper.selectByPrimaryKey(groupId);
        GroupDTO groupDTO = commonMapper.getGroupDTOByGroupId(groupId);
        return groupDTO;
    }


    //拼购订单加入拼购组，要判断加入后是否完成
    public PgGroup addToGroup(Long userId, String orderId, String groupId){
        //获取订单，判断订单是否有异常
        PgOrderMaster pgOrderMaster = pgOrderMasterMapper.selectByPrimaryKey(orderId);
        if(pgOrderMaster==null)
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(orderId));
        if(!pgOrderMaster.getUserId().equals(userId)){
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        }
        if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR);
        if(!pgOrderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_PAY_STATUS_ERROR, IdResultMap.getIdMap(orderId));

       //获取拼购组，判断拼购组是否有异常
        GroupDTO groupDTO = commonMapper.getGroupDTOByGroupId(groupId);
        if(groupDTO==null)
            throw new MadaoException(ErrorEnum.GROUP_NOT_EXIST, IdResultMap.getIdMap(groupId));
        if(groupDTO.getActiveStatus().equals(GroupActiveStatusEnum.GROUP_INACTIVE.getCode()))
            throw new MadaoException(ErrorEnum.GROUP_NOT_ACTIVE, IdResultMap.getIdMap(groupId));
        if(groupDTO.getGroupStatus().equals(GroupStatusEnum.FINISH.getCode()) || groupDTO.getGroupCount().equals(groupDTO.getGroupNum()))
            throw new MadaoException(ErrorEnum.GROUP_GROUP_FULL, IdResultMap.getIdMap(groupId));
        if(checkIfHadOrderInGroupByUser(groupDTO, userId)){
            throw new MadaoException(ErrorEnum.USER_HAVE_ORDER_IN_GROUP, IdResultMap.getIdMap(groupId));
        }

        //更新拼购组信息
        groupDTO.setGroupCount(groupDTO.getGroupCount()+1);
        boolean flag = groupDTO.getGroupCount().equals(groupDTO.getGroupNum());
        if(flag){
            groupDTO.setGroupStatus(GroupStatusEnum.FINISH.getCode());
        }
        PgGroup pgGroup = new PgGroup();
        BeanUtils.copyProperties(groupDTO, pgGroup);
        groupMapper.updateByPrimaryKeySelective(pgGroup);

        //添加组成员
        addGroupMember(pgOrderMaster, pgGroup);
        //更新订单信息
        pgOrderMaster.setGroupId(groupDTO.getGroupId());
        pgOrderMaster.setOrderStatus(PgOrderEnum.IN_GROUP.getCode());
        pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        //如果拼购组已完成，更新各个订单
        if(flag){
            setGroupOrderFinish(groupId);
        }
        return pgGroup;
    }

    //根据组id更新拼购订单为已拼购完成的状态
    public void setGroupOrderFinish(String groupId){
        PgOrderMasterExample example = new PgOrderMasterExample();
        PgOrderMasterExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        List<PgOrderMaster> pgOrderMasterList = pgOrderMasterMapper.selectByExample(example);
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            pgOrderMaster.setOrderStatus(PgOrderEnum.IN_GROUP.getCode());
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
    }

    //根据订单添加进拼购组
    public GroupMember addGroupMember(PgOrderMaster pgOrderMaster, PgGroup group){
        User user = userMapper.selectByPrimaryKey(pgOrderMaster.getUserId());
        GroupMember groupMember = new GroupMember();
        groupMember.setMemberId(KeyUtil.genUniquKey());
        groupMember.setGroupId(group.getGroupId());
        groupMember.setOrderId(pgOrderMaster.getOrderId());
        groupMember.setUserId(pgOrderMaster.getUserId());
        groupMember.setUserName(pgOrderMaster.getUserName());
        groupMember.setUserHeadPath(user.getUserHeadPath());
        groupMemberMapper.insertSelective(groupMember);
        return groupMember;
    }

    //判断拼购组中是否已经有该用户的订单了
    public boolean checkIfHadOrderInGroupByUser(GroupDTO groupDTO, Long userId){
        for(GroupMember groupMember: groupDTO.getGroupMemberList()){
            if(groupMember.getUserId().equals(userId)){
                return true;
            }
        }
        return false;
    }

}
