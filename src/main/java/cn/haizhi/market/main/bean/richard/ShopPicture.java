package cn.haizhi.market.main.bean.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShopPicture extends BaseBean{

    private Long pictureId;

    private String picturePath;

    private Long shopId;

}