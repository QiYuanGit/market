package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum CartCategoryEnum {
    CARTITEM_PLAIN((byte)0, "普通购物车项"),
    CARTITEM_PG((byte)1, "拼购购物车项"),
    ;
    private Byte code;
    private String message;

    CartCategoryEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
