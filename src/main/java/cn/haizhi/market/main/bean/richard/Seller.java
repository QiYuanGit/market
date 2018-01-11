package cn.haizhi.market.main.bean.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Seller extends BaseBean{

    private Long sellerId;

    private String sellerName;

    private String sellerPhone;

    private String sellerPhoto;

    private String sellerAddress;

    private Integer sellerState;

    private String idcardNumber;

    private String idcardFpicture;

    private String idcardBpicture;

    private Date createTime;

    private Date updateTime;

}