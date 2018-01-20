package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.GroupInfo;
import cn.haizhi.market.main.bean.madao.GroupInfoExample;
import cn.haizhi.market.main.mapper.madao.GroupInfoMapper;
import cn.haizhi.market.other.converter.DateConverter;
import cn.haizhi.market.other.enums.ResultEnum;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.DateFormatUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Slf4j
public class GroupInfoService {
    @Autowired
    GroupInfoMapper groupInfoMapper;
    //更新或插入拼购信息
    public int dealWithGroupInfo(GroupInfo groupInfo) throws ParseException {
        if(groupInfo.getGroupDate()==null && groupInfo.getGroupInfoId()==null) {
            log.error("-----------NULL");
            throw new MadaoException(ErrorEnum.PARAM_ERROR);
        }
        if(groupInfo.getGroupInfoId()==null)
            groupInfo.setGroupInfoId(DateFormatUtil.DateToString(groupInfo.getGroupDate()));
        else if(groupInfo.getGroupDate()==null)
            //如果转换为日期出现错误，捕获异常后抛出自定义异常
            try {
                groupInfo.setGroupDate(DateFormatUtil.StringToDate(groupInfo.getGroupInfoId()));
            }catch (Exception e){
                log.error("----------转换错误");
                throw new MadaoException((ErrorEnum.PARAM_ERROR));
            }
        //如果日期和id不一致，抛出异常
        if(!groupInfo.getGroupInfoId().equals(DateFormatUtil.DateToString(groupInfo.getGroupDate()))){
                log.error("-----------不一致");
            throw new MadaoException(ErrorEnum.PARAM_ERROR);
        }
        Long n = groupInfo.getGroupDate().getTime();
        Long n1 = new Date().getTime();
        Long nr = n - n1;
        if(groupInfo.getGroupDate().getTime()-new Date().getTime()<0)
            throw new MadaoException(ErrorEnum.PG_GROUP_HAN_OVERDUE);
        //如果拼购组信息已存在，进行更新。若不存在，执行插入
        int result = 0;
        if (checkGroupInfoExist(groupInfo.getGroupInfoId())){
            result = groupInfoMapper.updateByPrimaryKeySelective(groupInfo);
        }else {
            result = groupInfoMapper.insertSelective(groupInfo);
        }
        return result;
    }

    //获取所有组
    public List<GroupInfo> getGroupInfoList(){
        List<GroupInfo> list = groupInfoMapper.selectByExample(null);
        return list;
    }

    //根据id判断该日期的拼购信息项是否存在
    public boolean checkGroupInfoExist(String dateStr){
        GroupInfoExample example = new GroupInfoExample();
        GroupInfoExample.Criteria criteria = example.createCriteria();
        criteria.andGroupInfoIdEqualTo(dateStr);
        Long result = groupInfoMapper.countByExample(example);
        if(result<=0)
            return false;
        return true;
    }




    //获取指定日期的拼购组设置的数量
    public int getGroupNumByDate(Date date){
        GroupInfo info = groupInfoMapper.selectByPrimaryKey(DateFormatUtil.DateToString(date));
        if (info==null){
            throw new ResultException("当日拼购信息尚未设置");
        }
        return info.getGroupNum();
    }
}
