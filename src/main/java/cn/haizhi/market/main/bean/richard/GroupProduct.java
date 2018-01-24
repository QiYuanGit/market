package cn.haizhi.market.main.bean.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupProduct extends BaseBean{

    private Long productId;

    private String productName;

    private BigDecimal productOprice;

    private BigDecimal productNprice;

    private Integer productStock;

    private String productUnit;

    private String productDesc;

    private BigDecimal sendPrice;

    private Long categoryId;

    private Long sellerId;

}