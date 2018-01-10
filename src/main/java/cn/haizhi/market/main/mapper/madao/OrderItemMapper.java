package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.OrderItem;
import cn.haizhi.market.main.bean.madao.OrderItemExample;
import java.util.List;

public interface OrderItemMapper {
    long countByExample(OrderItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}