package cn.haizhi.market.other.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class FormErrorUtil {
    public static Map<String, String> getFormErrors(BindingResult bindingResult){
        Map<String, String> map = new HashMap<>();
        for(FieldError error: bindingResult.getFieldErrors()){
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
}
