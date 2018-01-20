package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.CartShop;
import cn.haizhi.market.main.bean.madao.CartShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartShopMapper {
    long countByExample(CartShopExample example);

    int deleteByExample(CartShopExample example);

    int insert(CartShop record);

    int insertSelective(CartShop record);

    List<CartShop> selectByExample(CartShopExample example);

    int updateByExampleSelective(@Param("record") CartShop record, @Param("example") CartShopExample example);

    int updateByExample(@Param("record") CartShop record, @Param("example") CartShopExample example);
}