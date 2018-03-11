package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class ProductShop {
    private Long shopId;
    private String shopName;
    private Long productId;
    private Integer productStock;

    public ProductShop() {
    }

    public ProductShop(Long shopId, String shopName, Long productId, Integer productStock) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.productId = productId;
        this.productStock = productStock;
    }
}
