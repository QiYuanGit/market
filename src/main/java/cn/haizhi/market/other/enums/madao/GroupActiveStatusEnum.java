package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum GroupActiveStatusEnum {
    GROUP_ACTIVE((byte)0, "组有效"),
    GROUP_INACTIVE((byte)1, "组无效"),
    ;
    private Byte code;
    private String message;

    GroupActiveStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
