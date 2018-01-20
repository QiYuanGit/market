package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum GroupStatusEnum {
    WAIT((byte)0, "等待组队"),
    FINISH((byte)1, "组队完成"),
    ;
    private Byte code;
    private String message;

     GroupStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
