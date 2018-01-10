package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderMaster {
    private String orderId;

    private Long shopId;

    private String shopName;

    private Long userId;

    private String userName;

    private String userPhone;

    private String userAddress;

    private BigDecimal orderAmount;

    private Byte orderStatus;

    private Byte payStatus;

    private Byte deliveryStatus;

    private Byte commentStatus;

    private Byte payWay;

    private String payAcount;

    private Date deliveryTime;

    private Date createTime;

    private Date updateTime;
}