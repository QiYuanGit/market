package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class CartItem {
    private String itemId;

    private String cartId;

    private Long userId;

    private Long shopId;

    private String shopName;

    private Long productId;

    private Integer productQuantity;

    private Byte itemCategory;


}