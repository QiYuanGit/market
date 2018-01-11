package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.bean.richard.ShopExample;
import cn.haizhi.market.main.view.richard.ShopView;

import java.util.List;

public interface ShopMapper {

    ShopView getoneWithJoin(Long id);

    List<ShopView> getallWithJoin(Shop shopForm);

    long countByExample(ShopExample example);

    int deleteByPrimaryKey(Long shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    List<Shop> selectByExample(ShopExample example);

    Shop selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}