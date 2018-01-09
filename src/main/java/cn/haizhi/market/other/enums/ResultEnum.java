package cn.haizhi.market.other.enums;

import lombok.Getter;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Getter
public enum ResultEnum {

    FAILURE_RESULT(0,"失败！"),
    SUCCESS_RESULT(1,"成功！"),
    ERROR_RESULT(2,"错误！");

    ResultEnum(Integer code, String hint){
        this.code = code;
        this.hint = hint;
    }

    private Integer code;
    private String hint;

}
