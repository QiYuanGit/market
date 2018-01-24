package cn.haizhi.market.main.view.richard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2018/1/24
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupProductView {

    private Long productId;

    private String productName;

    private BigDecimal productOprice;

    private BigDecimal productNprice;

    private Integer productStock;

    private String productUnit;

    private String productDesc;

    private BigDecimal sendPrice;

    private List<GroupProductPictureView> productPictures = new ArrayList<>();

    public void addPicture(GroupProductPictureView pictureView){
        productPictures.add(pictureView);
    }
}
