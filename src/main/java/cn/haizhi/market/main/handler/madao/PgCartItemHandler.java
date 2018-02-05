package cn.haizhi.market.main.handler.madao;

import cn.haizhi.market.main.bean.madao.PgCartItemDTO;
import cn.haizhi.market.main.service.madao.PgCartItemService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.form.madao.CartItemDeleteForm;
import cn.haizhi.market.other.form.madao.CartItemEmptyForm;
import cn.haizhi.market.other.form.madao.CartItemReviseForm;
import cn.haizhi.market.other.form.madao.PgCartItemForm;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static cn.haizhi.market.other.util.FormErrorUtil.getFormErrors;

@RestController
public class PgCartItemHandler {

    @Autowired
    PgCartItemService pgCartItemService;

    //添加进购物车
    @PostMapping("/pgCartItem")
    public ResultView addPgCartItem(@Valid @RequestBody PgCartItemForm pgCartItemForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        String id = pgCartItemService.addCartItem(pgCartItemForm);
        return ResultUtil.returnSuccess(IdResultMap.getIdMap(id));
    }

    //获取购物车列表
    @GetMapping("/pgCartItem")
    public ResultView getCartItemListByUserId(@RequestParam("userId") Long userId) {
        return ResultUtil.returnSuccess(pgCartItemService.getPgCartItemList(userId));
    }

    //获取购物车列表-分页
    @GetMapping("/pgCartItem/page")
    public ResultView getPgCartItemListByUserIdInPage(@RequestParam("userId") Long userId, @RequestParam(value = "pageNum", defaultValue = "10") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PgCartItemDTO> pgCartItemList = pgCartItemService.getPgCartItemList(userId);
        PageInfo pageInfo = new PageInfo(pgCartItemList);
        return ResultUtil.returnSuccess(pageInfo);
    }

    //根据购物车id删除
    @DeleteMapping("/pgCartItem")
    public ResultView deleteCartItem(@Valid @RequestBody CartItemDeleteForm cartItemDeleteForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        pgCartItemService.deleteCartItemById(cartItemDeleteForm);
        return ResultUtil.returnSuccess();
    }

    //根据用户id清空购物车
    @DeleteMapping("/pgCartItem/empty")
    public ResultView emptyCartItemByUserId(@Valid @RequestBody CartItemEmptyForm cartItemEmptyForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        pgCartItemService.deleteCartItemByUserId(cartItemEmptyForm);
        return ResultUtil.returnSuccess();
    }

    //修改购物车数量
    @PutMapping("/pgCartItem")
    public ResultView updateCartItem(@Valid @RequestBody CartItemReviseForm cartItemReviseForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        pgCartItemService.setPgCartItemQuantity(cartItemReviseForm);
        return ResultUtil.returnSuccess(IdResultMap.getIdMap(cartItemReviseForm.getCartItemId()));
    }

}
