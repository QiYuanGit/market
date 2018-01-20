package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum CartItemCategoryEnum {
    PLAIN_ITEM((byte)0, "普通订单"),
    GROUP_ITEM((byte)1, "拼购订单"),
    ;
    private Byte code;
    private String message;

    CartItemCategoryEnum(byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
