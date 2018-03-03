package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.Seller;
import cn.haizhi.market.main.service.richard.SellerService;
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
public class SellerHandler {

    @Autowired
    private SellerService sellerService;

    @PostMapping(value = "/seller",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody Seller form){
        sellerService.insert(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/seller",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody Seller form){
        sellerService.update(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/seller/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        sellerService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/seller/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(sellerService.selectOne(id));
    }

    @GetMapping(value = "/sellers",produces = "application/json; charset=UTF-8")
    public ResultView getall(Seller form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            return ResultUtil.returnSuccess(new PageInfo<>(sellerService.selectLot(form)));
        }else{
            return ResultUtil.returnSuccess(sellerService.selectLot(form));
        }
    }
}
