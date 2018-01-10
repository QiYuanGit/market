package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.GroupInfo;
import cn.haizhi.market.main.bean.madao.GroupInfoExample;
import java.util.List;

public interface GroupInfoMapper {
    long countByExample(GroupInfoExample example);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExample(GroupInfoExample example);
}