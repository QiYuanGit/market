package cn.haizhi.market.other.web;


import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.ResultEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.exception.qiyuan.QiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Date: 2018/1/8
 * Author: Richard
 */

public class ResultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        System.out.println("经过异常处理！");
        //如果是自定义异常，否则打印系统异常
        ResultView resultView = new ResultView();
        if (exception instanceof QiException){
            QiException qiException = (QiException) exception;
            resultView.setCode(qiException.getCode());
            resultView.setHint(qiException.getHint());
            System.out.println(qiException.getHint());
        }else if(exception instanceof MadaoException){
            ResultException resultException = (ResultException) exception;
            resultView.setCode(ResultEnum.FAILURE_RESULT.getCode());
            resultView.setHint(ResultEnum.FAILURE_RESULT.getHint()+resultException.getHint());
            System.out.println(resultException.getHint());
        }else if(exception instanceof ResultException){
            ResultException resultException = (ResultException) exception;
            resultView.setCode(ResultEnum.FAILURE_RESULT.getCode());
            resultView.setHint(ResultEnum.FAILURE_RESULT.getHint()+resultException.getHint());
            System.out.println(resultException.getHint());
        }else{
            resultView.setCode(ResultEnum.ERROR_RESULT.getCode());
            resultView.setHint(ResultEnum.ERROR_RESULT.getHint());
            exception.printStackTrace();
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.flush();
            printWriter.print(new ObjectMapper().writeValueAsString(resultView));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
