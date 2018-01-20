package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum CommentStatusEnum {
    WAIT((byte)0, "未评价"),
    FINISH((byte)1, "已评价"),
    ;
    private Byte code;

    CommentStatusEnum(Byte code, String messgae) {
        this.code = code;
        this.messgae = messgae;
    }

    private String messgae;
}
