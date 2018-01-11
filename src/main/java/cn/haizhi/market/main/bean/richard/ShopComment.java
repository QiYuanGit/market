package cn.haizhi.market.main.bean.richard;


import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShopComment extends BaseBean {

    private Long commentId;

    private Integer commentGrade;

    private String commentContent;

    private String commentPicture;

    private Long shopId;

    private Long userId;

    private Long orderId;

}