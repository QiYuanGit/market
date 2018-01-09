package cn.haizhi.market.other.exception;

import lombok.Getter;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Getter
public class ResultException extends RuntimeException{

    private String hint;

    public ResultException(String hint){
        this.hint = hint;
    }

}
