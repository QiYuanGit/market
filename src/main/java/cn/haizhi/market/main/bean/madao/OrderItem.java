package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class OrderItem {
    private String itemId;

    private String orderId;

    private Long productId;

    private String productName;

    private Long productPrice;

    private Integer productQuantity;

    private String productDesc;

    private String productIcon;

    private String itemRemark;

    public OrderItem(String itemId, String orderId, Long productId, String productName, Long productPrice, Integer productQuantity, String productDesc, String productIcon, String itemRemark) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDesc = productDesc;
        this.productIcon = productIcon;
        this.itemRemark = itemRemark;
    }

    public OrderItem() {
    }
}