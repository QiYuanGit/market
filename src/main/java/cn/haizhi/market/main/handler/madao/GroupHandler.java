package cn.haizhi.market.main.handler.madao;

import cn.haizhi.market.main.bean.madao.GroupDTO;
import cn.haizhi.market.main.bean.madao.PgGroup;
import cn.haizhi.market.main.service.madao.GroupService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.enums.madao.GroupAddForm;
import cn.haizhi.market.other.enums.madao.GroupCreateForm;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.util.FormErrorUtil;
import cn.haizhi.market.other.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupHandler {
    @Autowired
    GroupService groupService;

    //新建购物组
    @PostMapping("/group")
    public ResultView createGroup(@Valid @RequestBody GroupCreateForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, FormErrorUtil.getFormErrors(bindingResult));
        }
        PgGroup group = groupService.createGroup(form.getUserId(), form.getOrderId());
        return ResultUtil.returnSuccess(group);
    }

    //加入购物组
    @PutMapping("/group")
    public ResultView addGroup(@Valid @RequestBody GroupAddForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, FormErrorUtil.getFormErrors(bindingResult));
        }
        PgGroup group = groupService.addToGroup(form.getUserId(), form.getOrderId(),form.getGroupId());
        return ResultUtil.returnSuccess(group);
    }

    //根据订单查询购物组
    @GetMapping("/{orderId}/{userId}/group")
    public ResultView getGroupByOrder(@PathVariable(name = "userId") Long userId, @PathVariable(name = "orderId") String orderId){
        GroupDTO groupDTO = groupService.getGroupByOne(userId, orderId);
        return ResultUtil.returnSuccess(groupDTO);
    }


    //查询购物组
    @GetMapping("/{groupSatus}/{activeStatus}/group/list")
    public ResultView getGroup(@PathVariable("groupSatus") Byte groupStatus, @PathVariable("activeStatus") Byte activeStatus){
        List<GroupDTO> groupDTOList = groupService.getGroupList(groupStatus, activeStatus);
        return ResultUtil.returnSuccess(groupDTOList);
    }
}
