package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.service.richard.ProductService;
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
public class ProductHandler {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody Product form){
        productService.insertOne(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/product",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody Product form){
        productService.updateOne(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/product/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        productService.deleteOne(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/product/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(productService.selectOne(id));
    }

    @GetMapping(value = "/products",produces = "application/json; charset=UTF-8")
    public ResultView getall(Product form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
           return ResultUtil.returnSuccess(new PageInfo<>(productService.selectLot(form)));
        }else{
            return ResultUtil.returnSuccess(productService.selectLot(form));
        }
    }
}
