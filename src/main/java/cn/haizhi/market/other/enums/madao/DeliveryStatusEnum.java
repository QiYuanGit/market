package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum DeliveryStatusEnum {
    WAIT((byte)0, "未配送"),
    DELIVERYING((byte)1, "已配送"),
    FINSH((byte)2, "已完成"),


    ;
    private Byte code;
    private String message;

    DeliveryStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
