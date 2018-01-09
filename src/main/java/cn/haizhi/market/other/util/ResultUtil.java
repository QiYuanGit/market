package cn.haizhi.market.other.util;


import cn.haizhi.market.other.enums.ResultEnum;
import cn.haizhi.market.main.view.ResultView;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

public class ResultUtil {

    public static ResultView returnFailure(){
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.FAILURE_RESULT.getCode());
        resultView.setHint(ResultEnum.FAILURE_RESULT.getHint());
        return resultView;
    }

    public static ResultView returnFailure(String hint){
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.FAILURE_RESULT.getCode());
        resultView.setHint(ResultEnum.FAILURE_RESULT.getHint()+hint);
        return resultView;
    }

    public static ResultView returnSuccess(String hint){
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.SUCCESS_RESULT.getCode());
        resultView.setHint(ResultEnum.SUCCESS_RESULT.getHint());
        return resultView;
    }

    public static ResultView returnSuccess(Object data){
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.SUCCESS_RESULT.getCode());
        resultView.setHint(ResultEnum.SUCCESS_RESULT.getHint());
        resultView.setData(data);
        return resultView;
    }
}
