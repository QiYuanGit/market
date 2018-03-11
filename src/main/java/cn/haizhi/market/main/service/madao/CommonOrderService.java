package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.CommonOrder;
import cn.haizhi.market.main.mapper.madao.CommonMapper;
import cn.haizhi.market.other.enums.madao.OrderTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommonOrderService {
    @Autowired
    CommonMapper commonMapper;

    public List<CommonOrder> getCommonOrder(Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus){
        List<CommonOrder> orderlist = commonMapper.getCommonOrderList(null, userId, orderStatus, payStatus, deliveryStatus, commentStatus);
        List<CommonOrder> pgOrderList = commonMapper.getPgCommonOrderList(userId, orderStatus, payStatus, deliveryStatus, commentStatus);
        for(CommonOrder commonOrder: pgOrderList){
            commonOrder.setOrderType(OrderTypeEnum.PG_ORDER.getCode());
        }
        orderlist.addAll(pgOrderList);
        Collections.sort(orderlist);
        return orderlist;
    }
}
