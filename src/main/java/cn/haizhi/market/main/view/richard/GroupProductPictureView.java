package cn.haizhi.market.main.view.richard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Date: 2018/1/24
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GroupProductPictureView {

    private String picturePath;
}
