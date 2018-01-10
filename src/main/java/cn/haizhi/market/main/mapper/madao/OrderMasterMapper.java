package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.OrderMaster;
import cn.haizhi.market.main.bean.madao.OrderMasterExample;
import java.util.List;

public interface OrderMasterMapper {
    long countByExample(OrderMasterExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    List<OrderMaster> selectByExample(OrderMasterExample example);

    OrderMaster selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);
}