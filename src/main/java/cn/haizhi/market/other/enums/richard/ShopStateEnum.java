package cn.haizhi.market.other.enums.richard;

import lombok.Getter;

/**
 * Date: 2018/1/10
 * Author: Richard
 */

@Getter
public enum ShopStateEnum {

    IS_CLOSED(0,"关闭"),
    IS_OPEN(1,"开放");

    private Integer code;
    private String hint;

    ShopStateEnum(Integer code, String hint) {
        this.code = code;
        this.hint = hint;
    }
}
