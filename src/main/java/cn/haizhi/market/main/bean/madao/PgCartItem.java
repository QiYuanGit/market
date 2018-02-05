package cn.haizhi.market.main.bean.madao;

import lombok.Data;

@Data
public class PgCartItem {
    private String itemId;

    private Long userId;

    private Long productId;

    private Integer productQuantity;

}