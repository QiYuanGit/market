package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class CartItem {
    private String itemId;

    private Long userId;

    private Long shopId;

    private String shopName;

    private Long productId;

    private Integer productQuantity;

    private Byte itemCategory;

    public CartItem(String itemId, Long userId, Long shopId, String shopName, Long productId, Integer productQuantity, Byte itemCategory) {
        this.itemId = itemId;
        this.userId = userId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.itemCategory = itemCategory;
    }

    public CartItem() {
    }
}