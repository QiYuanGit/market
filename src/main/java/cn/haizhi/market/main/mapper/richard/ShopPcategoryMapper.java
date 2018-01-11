package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.ShopPcategory;
import cn.haizhi.market.main.bean.richard.ShopPcategoryExample;
import java.util.List;

public interface ShopPcategoryMapper {
    long countByExample(ShopPcategoryExample example);

    int deleteByPrimaryKey(Long joinId);

    int insert(ShopPcategory record);

    int insertSelective(ShopPcategory record);

    List<ShopPcategory> selectByExample(ShopPcategoryExample example);

    ShopPcategory selectByPrimaryKey(Long joinId);

    int updateByPrimaryKeySelective(ShopPcategory record);

    int updateByPrimaryKey(ShopPcategory record);
}