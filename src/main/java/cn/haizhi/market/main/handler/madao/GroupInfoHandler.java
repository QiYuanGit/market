package cn.haizhi.market.main.handler.madao;


import cn.haizhi.market.main.bean.madao.GroupInfo;
import cn.haizhi.market.main.service.madao.GroupInfoService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

import static cn.haizhi.market.other.util.FormErrorUtil.getFormErrors;

@RestController
public class GroupInfoHandler {

    @Autowired
    GroupInfoService service;

    //获取拼购组信息 分页
    @GetMapping("/groupinfo/page")
    public ResultView getGroupInfoPage(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<GroupInfo> groupInfoList = service.getGroupInfoList();
        PageInfo<GroupInfo> pageInfo = new PageInfo<>(groupInfoList);
        return ResultUtil.returnSuccess(pageInfo);
    }

    //获取拼购组信息
    @GetMapping("/groupinfo")
    public ResultView getGroupInfoList(){
        List<GroupInfo> list = service.getGroupInfoList();
        return ResultUtil.returnSuccess(list);
    }

    //添加或修改拼购组信息
    @PostMapping("/groupinfo")
    public ResultView addGroup(@Valid @RequestBody GroupInfo groupInfo, BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        int result = service.dealWithGroupInfo(groupInfo);

        if(result<=0)
            throw new ResultException("未更新拼购组信息");
        return ResultUtil.returnSuccess();
    }
}
