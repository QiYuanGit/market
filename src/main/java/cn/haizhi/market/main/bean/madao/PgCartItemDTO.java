package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PgCartItemDTO {
    private String itemId;

    private Long userId;

    private Integer productQuantity;

    private Long productId;

    private String productName;

    private BigDecimal productOprice;

    private BigDecimal productNprice;

    private Integer productStock;

    private String productUnit;

    private String productDesc;

    private String picturePath;

}
