package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum DiscountStateEnum {
    NOT_NO_DISCOUNT((byte)0, "没打折"),
    ON_DISCOUNT((byte)1, "打折"),
    ;
    private Byte code;
    private String message;

    DiscountStateEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
