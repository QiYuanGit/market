package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO{
    private String orderId;

    private Long shopId;

    private String shopName;

    private Long userId;

    private String userName;

    private String userPhone;

    private String userAddress;

    private BigDecimal productAmount;

    private BigDecimal sendPrice;

    private BigDecimal orderAmount;

    private Byte orderStatus;

    private Byte payStatus;

    private Byte deliveryStatus;

    private Byte commentStatus;

    private Byte payWay;

    private String payAcount;

    private Date deliveryTime;

    private Date arriveTime;

    private Date receiveTime;

    private Long commentId;

    private Date createTime;

    private Date updateTime;

    private String orderRemark;


    private List<OrderItem> orderItemList = new ArrayList<>();
}
