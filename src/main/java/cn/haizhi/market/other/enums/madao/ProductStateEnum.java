package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum  ProductStateEnum {
    ON_SALE((byte)1, "商品在架"),
    DOWN_SALE((byte)0, "商品下架"),
    ;
    private Byte code;
    private String message;

    ProductStateEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
