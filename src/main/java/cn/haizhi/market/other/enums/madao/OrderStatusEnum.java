package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW((byte)0, "新订单"),
    FINISH((byte)1, "已完成"),
    TO_CANCEL((byte)2, "发起取消"),
    CANCEL((byte)3, "已取消"),
    ;
    private Byte code;
    private String message;

    OrderStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
