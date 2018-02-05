package cn.haizhi.market.main.handler.madao;

import cn.haizhi.market.main.bean.madao.CartItemDTO;
import cn.haizhi.market.main.bean.madao.CartShopDTO;
import cn.haizhi.market.main.service.madao.CartItemService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.CartItemCategoryEnum;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.form.madao.CartItemDeleteForm;
import cn.haizhi.market.other.form.madao.CartItemEmptyForm;
import cn.haizhi.market.other.form.madao.CartItemForm;
import cn.haizhi.market.other.form.madao.CartItemReviseForm;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.haizhi.market.other.util.FormErrorUtil.getFormErrors;

@RestController

public class CartItemHandler {
    @Autowired
    CartItemService cartItemService;

    //根据用户id和购物车类别获取购物车列表
    @GetMapping("/cartItem")
    public ResultView getCartItemListByUserId(@RequestParam("userId") Long userId) {
       return ResultUtil.returnSuccess(cartItemService.getCartListByUserId(userId));
    }

    //获取购物车列表 分页
    @GetMapping("/cartItem/page")
    public ResultView getCartItemListByUserId(@RequestParam("userId") Long userId, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @RequestParam(value="pageSize", defaultValue="10") Integer pageSize){
        PageInfo pageInfo = cartItemService.getCartListByUserIdInPage(userId, pageNum, pageSize);
        return ResultUtil.returnSuccess(pageInfo);
    }


    //添加购物车
    @PostMapping("/cartItem")
    public ResultView addCartItem(@Valid @RequestBody CartItemForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        String id = cartItemService.addCartItem(form);
        return ResultUtil.returnSuccess(IdResultMap.getIdMap(id));
    }

    //根据购物车id删除
    @DeleteMapping("/cartItem")
    public ResultView deleteCartItem(@Valid @RequestBody CartItemDeleteForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        cartItemService.deleteCartItemById(form.getUserId(), form.getCartItemId());
        return ResultUtil.returnSuccess();
    }

    //根据用户id删除，清空用户购物车
    @DeleteMapping("/cartItem/empty")
    public ResultView emptyCartItem(@Valid @RequestBody CartItemEmptyForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        cartItemService.deleteCartItemByUserId(form.getUserId());
        return ResultUtil.returnSuccess();
    }

    //修改购物车数量
    @PutMapping("/cartItem")
    public ResultView updateCartItem(@Valid @RequestBody CartItemReviseForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        cartItemService.updateCartItemQuantity(form.getUserId(), form.getCartItemId(), form.getQuantity());
        return ResultUtil.returnSuccess(IdResultMap.getIdMap(form.getCartItemId()));
    }
}
