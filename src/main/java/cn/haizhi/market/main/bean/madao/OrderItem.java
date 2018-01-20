package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private String itemId;

    private String orderId;

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productDesc;

    private String productIcon;

}