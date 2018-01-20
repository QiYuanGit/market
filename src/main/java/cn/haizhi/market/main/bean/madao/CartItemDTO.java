package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private String itemId;

    private Long userId;

    private Long shopId;

    private String shopName;

    private Long productId;

    private Integer productQuantity;

    private Byte itemCategory;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal discountPrice;

    private String productIcon;

    private String productDesc;

    private String productState;



}