package cn.haizhi.market.other.util;

import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

public class BeanUtil {

    public static String isLike(String string){
        try {
            return "%"+new String(string.getBytes("iso-8859-1"),"utf-8")+"%";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyBean(Object source,Object target){
        BeanUtils.copyProperties(source,target);
    }

    public static Boolean isNull(Object object){
        return (object == null);
    }

    public static Boolean notNull(Object object){
        return (object != null);
    }

    public static Boolean isEmpty(String string){
        return (string == null || string.trim().isEmpty());
    }

    public static Boolean notEmpty(String string){
        return (string != null && !string.trim().isEmpty());
    }

    public static Boolean notEmpty(List list){
        return (list != null && list.size() > 0);
    }

    public static synchronized Long getKey() {
        Integer number = new Random().nextInt(900000) + 100000;
        return System.currentTimeMillis() + number;
    }

}
