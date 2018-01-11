package cn.haizhi.market.main.bean.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Shop extends BaseBean {

    private Long shopId;

    private String shopName;

    private String shopAddress;

    private String shopPhone;

    private Integer shopSale;

    private String shopDesc;

    private Integer shopGrade;

    private BigDecimal limitPrice;

    private Integer shopState;

    private BigDecimal sendPrice;

    private Date workTime;

    private Boolean isRecom;

    private Integer recomOrder;

    private Long sellerId;

}