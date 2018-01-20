package cn.haizhi.market.other.util;

import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.ErrorEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorResultUtil {
    public static List<ResultView> getResultViewList(Map<ErrorEnum, List<String>> map){
        List<ResultView> resultViewList = new ArrayList<>();
        for(Map.Entry<ErrorEnum, List<String>> entry: map.entrySet()){
            ResultView resultView = new ResultView();
            resultView.setCode(entry.getKey().getCode());
            resultView.setHint(entry.getKey().getMessage());
            resultView.setData(IdResultMap.getIdMap(entry.getValue()));
            resultViewList.add(resultView);
        }
        return resultViewList;
    }

    public static List<ResultView> getResultViewListWithName(String name, Map<ErrorEnum, List<String>> map){
        List<ResultView> resultViewList = new ArrayList<>();
        for(Map.Entry<ErrorEnum, List<String>> entry: map.entrySet()){
            ResultView resultView = new ResultView();
            resultView.setCode(entry.getKey().getCode());
            resultView.setHint(entry.getKey().getMessage());
            resultView.setData(IdResultMap.getIdMapWithName(name, entry.getValue()));
            resultViewList.add(resultView);
        }
        return resultViewList;
    }
}
