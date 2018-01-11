package cn.haizhi.market.main.view.richard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Date: 2018/1/10
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductView {

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productIcon;

    private String productDesc;

    private String productNote;

    private String limitNumber;

}
