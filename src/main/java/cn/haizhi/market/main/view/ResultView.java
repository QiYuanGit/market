package cn.haizhi.market.main.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultView {

    private Integer code;
    private String hint;
    private Object data;

}
