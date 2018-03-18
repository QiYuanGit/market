package cn.haizhi.market.main.handler.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.service.qiyuan.UserAddressService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.form.qiyuan.UserAddressForm;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class UserAddressHandler {
    @Autowired
    private UserAddressService userAddressService;

    @PostMapping(value = "/userAddress/add",produces = "application/json;charset=UTF-8")
    public ResultView insert(@RequestBody UserAddress userAddress){
        UserAddress address = userAddressService.insert(userAddress);
        return ResultUtil.returnSuccess(address);
    }

    @PutMapping(value = "/userAddress/update",produces = "application/json;charset=UTF-8")
    public ResultView update(@RequestBody UserAddressForm form){
        UserAddress userAddress = userAddressService.update(form);
        return ResultUtil.returnSuccess(userAddress);
    }

    @DeleteMapping(value = "/userAddress/{id}",produces = "application/json;charset=UTF-8")
    public ResultView delete(@PathVariable("id") Long id){
        userAddressService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/userAddress/address/{id}",produces = "application/json;charset=UTF-8")
    public ResultView getOne(@PathVariable(name ="id") Long id){
        UserAddress userAddress = userAddressService.getOne(id);
        return ResultUtil.returnSuccess(userAddress);
    }

    @GetMapping(value = "/userAddress/getAll",produces = "application/json;charset=UTF-8")
    public ResultView getAll(UserAddress form) throws Exception{
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            return ResultUtil.returnSuccess(new PageInfo<>(userAddressService.getAll(form)));
        }else{
            return ResultUtil.returnSuccess(userAddressService.getAll(form));
        }
    }

    @GetMapping(value = "/userAddress/user/{id}",produces = "application/json;charset=UTF-8")
    public ResultView getOneUserAllAddress(@PathVariable("id") Long id,HttpSession session){
        List<UserAddress> userAddressList = userAddressService.getOneUserAllAddress(id,session);
        return ResultUtil.returnSuccess(userAddressList );
    }

    @PostMapping(value = "/userAddress/updateDefaultAddress",produces = "application/json;charset=UTF-8")
    public ResultView updateDefaultAddress(@RequestBody UserAddress userAddress,HttpSession session){
        userAddressService.updateDefaultAddress(userAddress,session);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/userAddress/getDefaultAddress/{id}",produces = "application/json;charset=UTF-8")
    public ResultView getDefaultAddress(@PathVariable("id") Long id, HttpSession session){
        UserAddress userAddress = userAddressService.getDefaultAddress(id,session);
        return ResultUtil.returnSuccess(userAddress);
    }

}
