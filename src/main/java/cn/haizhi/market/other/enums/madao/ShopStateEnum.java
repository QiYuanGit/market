package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum ShopStateEnum {
    ON((byte)1, "商店在线"),
    DOWN((byte)2, "商店下线")
    ;

    private Byte code;
    private String message;

    ShopStateEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }

}
