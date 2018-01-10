package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.CartItem;
import cn.haizhi.market.main.bean.madao.CartItemExample;
import java.util.List;

public interface CartItemMapper {
    long countByExample(CartItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    List<CartItem> selectByExample(CartItemExample example);

    CartItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);
}