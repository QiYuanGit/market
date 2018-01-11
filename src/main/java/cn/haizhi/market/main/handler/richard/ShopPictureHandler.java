package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.ShopPicture;
import cn.haizhi.market.main.service.richard.ShopPictureService;
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
public class ShopPictureHandler {

    @Autowired
    private ShopPictureService shopPictureService;

    @PostMapping(value = "/shopPicture",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody ShopPicture form){
        shopPictureService.insert(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/shopPicture",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody ShopPicture form){
        shopPictureService.update(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/shopPicture/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        shopPictureService.delete(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/shopPicture/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(shopPictureService.getone(id));
    }

    @GetMapping(value = "/shopPictures",produces = "application/json; charset=UTF-8")
    public ResultView getall(ShopPicture form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            return ResultUtil.returnSuccess(new PageInfo<>(shopPictureService.getall(form)));
        }else{
            return ResultUtil.returnSuccess(shopPictureService.getall(form));
        }
    }
}
