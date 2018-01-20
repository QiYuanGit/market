package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class ProductShop {
    private Long shopId;
    private String shopName;
    private Long productId;
    private Byte discountState;

    public ProductShop() {
    }

    public ProductShop(Long shopId, String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
    }
}
