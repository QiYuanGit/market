package cn.haizhi.market.other.enums.madao;

public enum  PayWayEnum {
    ALIPAY((byte)0, "支付宝"),
    WECHAT((byte)1, "微信"),
    ;
    private Byte code;
    private String type;

    PayWayEnum(Byte code, String type) {
        this.code = code;
        this.type = type;
    }
}
