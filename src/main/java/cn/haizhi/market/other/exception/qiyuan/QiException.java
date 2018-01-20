package cn.haizhi.market.other.exception.qiyuan;

import lombok.Getter;

@Getter
public class QiException extends RuntimeException{
    private Integer code;
    private String hint;

    public QiException(Integer code,String hint){
        this.code = code;
        this.hint = hint;
    }
}
