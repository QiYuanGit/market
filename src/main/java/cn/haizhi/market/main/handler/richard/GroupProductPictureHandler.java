package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.GroupProductPicture;
import cn.haizhi.market.main.service.richard.GroupProductPictureService;
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
public class GroupProductPictureHandler {

    @Autowired
    private GroupProductPictureService groupProductPictureService;

    @PostMapping(value = "/groupProductPicture",produces = "application/json; charset=UTF-8")
    public ResultView insert(@RequestBody GroupProductPicture form){
        groupProductPictureService.insertOne(form);
        return ResultUtil.returnSuccess();
    }

    @PutMapping(value = "/groupProductPicture",produces = "application/json; charset=UTF-8")
    public ResultView update(@RequestBody GroupProductPicture form){
        groupProductPictureService.updateOne(form);
        return ResultUtil.returnSuccess();
    }

    @DeleteMapping(value = "/groupProductPicture/{id}",produces = "application/json; charset=UTF-8")
    public ResultView delete(@PathVariable("id")Long id){
        groupProductPictureService.deleteOne(id);
        return ResultUtil.returnSuccess();
    }

    @GetMapping(value = "/groupProductPicture/{id}",produces = "application/json; charset=UTF-8")
    public ResultView getone(@PathVariable("id")Long id){
        return ResultUtil.returnSuccess(groupProductPictureService.selectOne(id));
    }

    @GetMapping(value = "/groupProductPictures",produces = "application/json; charset=UTF-8")
    public ResultView getall(GroupProductPicture form) throws Exception {
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
           return ResultUtil.returnSuccess(new PageInfo<>(groupProductPictureService.selectLot(form)));
        }else{
            return ResultUtil.returnSuccess(groupProductPictureService.selectLot(form));
        }
    }
}
