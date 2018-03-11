package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum OrderTypeEnum {
    PLAIN_ORDER((byte)0, "普通订单"),
    PG_ORDER((byte)1, "拼购订单");
    ;
    private Byte code;
    private String message;

    OrderTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
