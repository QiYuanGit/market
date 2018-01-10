package cn.haizhi.market.other.util;


import cn.haizhi.market.other.enums.ResultEnum;
import cn.haizhi.market.main.view.ResultView;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

public class ResultUtil {

    public static ResultView returnSuccess(){
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
