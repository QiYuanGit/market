package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.GroupProduct;
import cn.haizhi.market.main.bean.richard.GroupProductExample;

import java.util.List;


public interface GroupProductMapper {
    long countByExample(GroupProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(GroupProduct record);

    int insertSelective(GroupProduct record);

    List<GroupProduct> selectByExample(GroupProductExample example);

    GroupProduct selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(GroupProduct record);

    int updateByPrimaryKey(GroupProduct record);
}