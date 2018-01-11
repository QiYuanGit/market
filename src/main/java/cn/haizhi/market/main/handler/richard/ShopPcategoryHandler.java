package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.ShopPcategory;
import cn.haizhi.market.main.service.richard.ShopPcategoryService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 2018/1/10
 * Author: Richard
 */


@RestController
public class ShopPcategoryHandler {

    @Autowired
    private ShopPcategoryService shopPcategoryService;

    @PostMapping(value = "/shopPcategory",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody ShopPcategory form){
        shopPcategoryService.insert(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/shopPcategory",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody ShopPcategory form){
        shopPcategoryService.update(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/shopPcategory/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        shopPcategoryService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/shopPcategory/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(shopPcategoryService.getone(id));
    }

    @GetMapping(value = "/shopPcategorys",produces = "application/json; charset=UTF-8")
    public ResultView getall(ShopPcategory form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            return ResultUtil.returnSuccess(new PageInfo<>(shopPcategoryService.getall(form)));
        }else{
            return ResultUtil.returnSuccess(shopPcategoryService.getall(form));
        }
    }
}
