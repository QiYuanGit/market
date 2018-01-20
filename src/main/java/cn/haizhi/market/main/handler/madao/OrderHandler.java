package cn.haizhi.market.main.handler.madao;

import cn.haizhi.market.main.bean.madao.OrderDTO;
import cn.haizhi.market.main.bean.madao.PgOrderDTO;
import cn.haizhi.market.main.service.madao.OrderService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.form.madao.RemarkForm;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.form.madao.*;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.haizhi.market.other.util.FormErrorUtil.getFormErrors;

@RestController
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResultView createOrder(@Valid @RequestBody OrderCreateForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<OrderDTO> orderDTOList = orderService.addOrder(orderForm);
        if(orderDTOList.size()==0){
            throw new ResultException(ErrorEnum.CREATE_ORDER_FAIL.getMessage());
        }
        return ResultUtil.returnSuccess(orderDTOList);
    }

    @PostMapping("/pgOrder")
    public ResultView createPgOrder(@Valid @RequestBody OrderCreateForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
//        List<PgOrderDTO> pgOrderDTOList = orderService.addPgOrder(orderForm);
        PgOrderDTO pgOrderDTO = orderService.addPgOrder(orderForm);
        if(pgOrderDTO.getOrderItemList().size()==0){
            throw new ResultException(ErrorEnum.CREATE_ORDER_FAIL.getMessage());
        }
        return ResultUtil.returnSuccess(pgOrderDTO);
    }

    //根据用户id、商铺id, 订单状态获取订单，订单状态可选
    @GetMapping("/order")
    public ResultView getOrderByUser(@RequestParam(value="shopId", required=false) Long shopId,  @RequestParam("userId") Long userId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus){
        return ResultUtil.returnSuccess(orderService.getOrderDTO(shopId, userId, orderStatus, payStatus, deliveryStatus, commentStatus));
    }

    @GetMapping("/order/page")
    public ResultView getOrderByUserInPage(@RequestParam(value="shopId", required=false) Long shopId,  @RequestParam("userId") Long userId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus, @RequestParam(value="pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDTO> orderDTOList = orderService.getOrderDTO(shopId, userId, orderStatus, payStatus, deliveryStatus, commentStatus);
        PageInfo pageInfo = new PageInfo(orderDTOList);
        return ResultUtil.returnSuccess(pageInfo);
    }

    //根据用户id和订单状态获取拼购订单
    @GetMapping("/pgOrder")
    public ResultView getPgOrderByUser(@RequestParam("userId") Long userId, @RequestParam(value="orderStatus", required = false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus){
        return ResultUtil.returnSuccess(orderService.getPgOrderDTO(userId, orderStatus, payStatus, deliveryStatus, commentStatus));
    }

    @GetMapping("/pgOrder/page")
    public ResultView getPgOrderByUserInPage(@RequestParam("userId") Long userId, @RequestParam(value="orderStatus", required = false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus, @RequestParam(value="pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PgOrderDTO> pgOrderDTOList = orderService.getPgOrderDTO(userId, orderStatus, payStatus, deliveryStatus, commentStatus);
        PageInfo pageInfo = new PageInfo(pgOrderDTOList);
        return ResultUtil.returnSuccess(pageInfo);
    }

    //商家调用查看订单
    @GetMapping("/order/shop")
    public ResultView getOrderByShop(@RequestParam(value="shopId", required=false) Long shopId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus){
        return ResultUtil.returnSuccess(orderService.getOrderDTO(shopId, null, orderStatus, payStatus, deliveryStatus, commentStatus));
    }

    @GetMapping("/order/shop/page")
    public ResultView getOrderByShopInfPage(@RequestParam(value="shopId", required=false) Long shopId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus, @RequestParam(value="pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDTO> orderDTOList = orderService.getOrderDTO(shopId, null, orderStatus, payStatus, deliveryStatus, commentStatus);
        PageInfo pageInfo = new PageInfo(orderDTOList);
        return ResultUtil.returnSuccess(pageInfo);
    }


    //商家带调用查看拼购订单
    @GetMapping("/pgOrder/shop")
    public ResultView getPgOrderByShop(@RequestParam(value="shopId", required=false) Long shopId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus){
        return ResultUtil.returnSuccess(orderService.getPgOrderDTO(null, orderStatus, payStatus, deliveryStatus, commentStatus));
    }

    @GetMapping("/pgOrder/shop/page")
    public ResultView getPgOrderByShop(@RequestParam(value="shopId", required=false) Long shopId, @RequestParam(value="orderStatus", required=false) Byte orderStatus, @RequestParam(value="payStatus", required=false) Byte payStatus, @RequestParam(value="deliveryStatus", required=false) Byte deliveryStatus, @RequestParam(value="commentStatus", required=false) Byte commentStatus, @RequestParam(value="pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PgOrderDTO> pgOrderDTOList = orderService.getPgOrderDTO(null, orderStatus, payStatus, deliveryStatus, commentStatus);
        PageInfo pageInfo = new PageInfo(pgOrderDTOList);
        return ResultUtil.returnSuccess(pageInfo);
    }

    //支付普通订单
    @PutMapping("/order")
    public ResultView payOrder(@Valid @RequestBody OrderUpdateForm orderUpdateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<OrderDTO> orderDTOList = orderService.payOrder(orderUpdateForm.getId(), orderUpdateForm.getOrderIdList());
        System.out.println(orderDTOList + "-----------" + orderDTOList.size());
        if(orderDTOList.size()==0){
            throw new ResultException(ErrorEnum.PAY_ORDER_ERROR.getMessage());
        }
        return ResultUtil.returnSuccess(orderDTOList);
    }

    //支付拼购订单
    @PutMapping("/pgOrder")
    public ResultView payPgOrder(@Valid @RequestBody OrderUpdateForm orderUpdateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<PgOrderDTO> pgOrderDTOList = orderService.payPgOrder(orderUpdateForm.getId(), orderUpdateForm.getOrderIdList());
        if(pgOrderDTOList.size()==0){
            throw new ResultException(ErrorEnum.PAY_ORDER_ERROR.getMessage());
        }
        return ResultUtil.returnSuccess(pgOrderDTOList);
    }

    //商家设置配送时间
    @PutMapping("/order/delivery")
    public ResultView setOrderDeliveryTime(@Valid @RequestBody OrderDateForm orderDateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<String> id = orderService.updateDeliveryTime(orderDateForm.getShopId(), orderDateForm.getOrderIdList(), orderDateForm.getDate(), null);
        return ResultUtil.returnSuccess(id);
    }

    @PutMapping("/pgOrder/delivery")
    public ResultView setPgOrderDeliveryTime(@Valid @RequestBody OrderDateForm orderDateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<String> idList = orderService.updateDeliveryTimePg(orderDateForm.getShopId(), orderDateForm.getOrderIdList(), orderDateForm.getDate(), null);
        return ResultUtil.returnSuccess(idList);
    }

    @PutMapping("/order/arrive")
    public ResultView setOrderArriveTime(@Valid @RequestBody OrderDateForm orderDateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<String> id = orderService.updateDeliveryTime(orderDateForm.getShopId(), orderDateForm.getOrderIdList(),  null, orderDateForm.getDate());
        return ResultUtil.returnSuccess(id);
    }

    @PutMapping("/pgOrder/arrive")
    public ResultView setPgOrderArriveTime(@Valid @RequestBody OrderDateForm orderDateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<String> id = orderService.updateDeliveryTimePg(orderDateForm.getShopId(), orderDateForm.getOrderIdList(),  null, orderDateForm.getDate());
        return ResultUtil.returnSuccess(id);
    }

    //用户取消订单
    @PutMapping("/order/cancel")
    public ResultView cancelOrderByUser(@Valid @RequestBody OrderIdForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<OrderDTO> result = orderService.cancelOrderByUser(form.getId(), form.getOrderIdList());
        if(result.size()==0)
            throw new MadaoException(ErrorEnum.ORDER_NOT_CANCEL);
        return ResultUtil.returnSuccess(result);
    }

    //商家取消订单
    @PutMapping("/order/cancel/shop")
    public ResultView cancelOrderByShop(@Valid @RequestBody OrderIdForm form, BindingResult bindingResult){
        List<OrderDTO> result = orderService.cancelOrderByShop(form.getId(), form.getOrderIdList());
        if(result.size()==0)
            throw new MadaoException(ErrorEnum.ORDER_NOT_CANCEL);
        return ResultUtil.returnSuccess(result);
    }

    //用户取消拼购订单
    @PutMapping("/pgOrder/cancel")
    public ResultView cancelPgOrderByUser(@Valid @RequestBody OrderIdForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<PgOrderDTO> result = orderService.cancelPgOrderByUser(form.getId(), form.getOrderIdList());
        if(result.size()==0)
            throw new MadaoException(ErrorEnum.ORDER_NOT_CANCEL);
        return ResultUtil.returnSuccess(result);
    }

    //商家取消拼购订单
    @PutMapping("/pgOrder/cancel/shop")
    public ResultView cancelPgOrderByShop(@Valid @RequestBody OrderIdForm pgOrderIdForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        List<String> orderIdList = pgOrderIdForm.getOrderIdList();
        if(orderIdList==null || orderIdList.size()==0)
            throw new ResultException("未选中订单");
        List<PgOrderDTO> result = orderService.cancelPgOrderByShop(orderIdList);
        if(result.size()==0)
            throw new MadaoException(ErrorEnum.ORDER_NOT_CANCEL);
        return ResultUtil.returnSuccess();
    }

    //用户确认收货 普通订单
    @PutMapping("/order/confirm")
    public ResultView confirmOrder(@Valid @RequestBody OrderIdForm orderIdForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.confirm(orderIdForm.getId(), orderIdForm.getOrderIdList());
        return ResultUtil.returnSuccess();
    }

    //用户确认收货  拼购订单
    @PutMapping("/pgOrder/confirm")
    public ResultView confirmPgOrder(@Valid @RequestBody OrderIdForm orderIdForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.confirmPg(orderIdForm.getId(), orderIdForm.getOrderIdList());
        return ResultUtil.returnSuccess();
    }


    //商家确认取消普通订单
    @PutMapping("/order/cancel/confirm/shop")
    public ResultView confirmCancel(@Valid @RequestBody OrderIdForm orderdForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.confirmCancel(orderdForm.getId(), orderdForm.getOrderIdList());
        return ResultUtil.returnSuccess();
    }

    //商家确认取消拼购订单
    @PutMapping("/pgOrder/cancel/confirm/shop")
    public ResultView confirmCancelPg(@Valid @RequestBody OrderIdForm orderdForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.confirmCancelPg(orderdForm.getId(), orderdForm.getOrderIdList());
        return ResultUtil.returnSuccess();
    }

    //用户普通订单留言
    @PutMapping("/order/remark")
    public ResultView remarkOrder(@Valid @RequestBody RemarkForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.setOrderRemark(form.getUserId(), form.getOrderId(), form.getRemark());
        return ResultUtil.returnSuccess();
    }

    //用户拼购订单留言
    @PutMapping("/pgOrder/remark")
    public ResultView remarkPgOrder(@Valid @RequestBody RemarkForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MadaoException(ErrorEnum.PARAM_ERROR, getFormErrors(bindingResult));
        }
        orderService.setPgOrderRemark(form.getUserId(), form.getOrderId(), form.getRemark());
        return ResultUtil.returnSuccess();
    }

    @GetMapping("/test")
    public OrderCreateForm test(){
        OrderCreateForm orderCreateForm = new OrderCreateForm();
        orderCreateForm.setAddressId(1L);
        orderCreateForm.setUserId(1L);
        orderCreateForm.setUserName("aaa");
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        orderCreateForm.setCartItemIdList(list);
        return orderCreateForm;
    }
    @GetMapping("/test2")
    public Date test2(){
        return new Date();
    }

    @GetMapping("/test3")
    public Date test3(){
        return new Date();
    }

    @GetMapping("/test4")
    public void test3(Long date){
        System.out.println(new Date(date));
    }
}
