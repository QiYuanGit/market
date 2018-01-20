package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum ProductTypeEnum {
    PRODUCT_PG((byte)1, "拼购商品"),
    PRODUCT_PLAIN((byte)0, "普通商品"),
    ;
    private byte code;
    private String message;

    ProductTypeEnum(byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
