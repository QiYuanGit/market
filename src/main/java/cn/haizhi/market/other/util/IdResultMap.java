package cn.haizhi.market.other.util;

import java.util.HashMap;
import java.util.Map;

public class IdResultMap {
    public static  Map<String, Object> getIdMap(Object id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    public static  Map<String, Object> getIdMapWithName(String name, Object id){
        Map<String, Object> map = new HashMap<>();
        map.put(name, id);
        return map;
    }
}
