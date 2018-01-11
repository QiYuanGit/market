package cn.haizhi.market.main.view.richard;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Date: 2018/1/10
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShopCommentView{

    private Long commentId;

    private Integer commentGrade;

    private String commentContent;

    private String commentPicture;

    private String userName;

    private String userHead;

}
