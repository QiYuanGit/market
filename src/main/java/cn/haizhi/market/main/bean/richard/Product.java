package cn.haizhi.market.main.bean.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product extends BaseBean {

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productIcon;

    private String productDesc;

    private Integer productState;

    private String productNote;

    private String limitNumber;

    private Integer discountState;

    private BigDecimal discountPrice;

    private Long categoryId;

    private Long shopId;

}