package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.service.richard.ShopService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@RestController
public class ShopHandler {

    @Autowired
    private ShopService shopService;

    @PostMapping(value = "/shop",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody Shop form){
        shopService.insert(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/shop",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody Shop form){
        shopService.update(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/shop/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        shopService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/shop/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(shopService.getone(id));
    }

    @GetMapping(value = "/shops",produces = "application/json; charset=UTF-8")
    public ResultView getall(Shop form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
           return ResultUtil.returnSuccess(new PageInfo<>(shopService.getall(form)));
        }else{
            return ResultUtil.returnSuccess(shopService.getall(form));
        }
    }
}
