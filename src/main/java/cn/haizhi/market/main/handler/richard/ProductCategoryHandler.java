package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.ProductCategory;
import cn.haizhi.market.main.service.richard.ProductCategoryService;
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
public class ProductCategoryHandler {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping(value = "/productCategory",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody ProductCategory form){
        productCategoryService.insert(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/productCategory",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody ProductCategory form){
        productCategoryService.update(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/productCategory/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        productCategoryService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/productCategory/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(productCategoryService.getone(id));
    }

    @GetMapping(value = "/productCategorys",produces = "application/json; charset=UTF-8")
    public ResultView getall(ProductCategory form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
           return ResultUtil.returnSuccess(new PageInfo<>(productCategoryService.getall(form)));
        }else{
            return ResultUtil.returnSuccess(productCategoryService.getall(form));
        }
    }
}
