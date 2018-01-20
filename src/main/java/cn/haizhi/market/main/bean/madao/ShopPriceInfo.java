package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopPriceInfo {
    private Long shopId;
    private Byte shopState;
    private BigDecimal limitPrice;
    private BigDecimal sendPrice;
}
