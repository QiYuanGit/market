package cn.haizhi.market.other.enums.madao;

public enum PuschaseGroupStatus {
    WAIT((byte)0, "未完成"),
    FINISH((byte)1, "已完成"),
    ;
    private Byte code;
    private String message;

    PuschaseGroupStatus(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
