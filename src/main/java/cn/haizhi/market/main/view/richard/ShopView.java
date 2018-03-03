package cn.haizhi.market.main.view.richard;

import cn.haizhi.market.other.util.SerializerUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 2018/1/10
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShopView {

    private Long shopId;

    private String shopName;

    private String shopAddress;

    private String shopPhone;

    private String shopDesc;

    private String shopNote;

    private Integer shopSale;

    private Integer shopGrade;

    private BigDecimal limitPrice;

    private BigDecimal sendPrice;

    @JsonSerialize(using = SerializerUtil.class)
    private Date workTime;

    private String sellerName;

    private List<ShopPictureView> shopPictures = new ArrayList<>();

    public void addPicture(ShopPictureView shopPictureView){
        shopPictures.add(shopPictureView);
    }
}
