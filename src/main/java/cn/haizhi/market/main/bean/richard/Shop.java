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

    private String shopNote;

    private Integer shopSale;

    private String shopIcon;

    private String shopDesc;

    private Integer shopGrade;

    private Integer shopState;

    private BigDecimal limitPrice;

    private BigDecimal sendPrice;

    private Date workTime;

    private Boolean isRecom;

    private Integer recomOrder;

    private Long sellerId;

}