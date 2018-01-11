package cn.haizhi.market.main.handler.richard;

import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.bean.richard.ShopComment;
import cn.haizhi.market.main.service.richard.FrontService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.other.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@RestController
@RequestMapping("/front")
public class FrontHandler {

    @Autowired
    private FrontService frontService;

    @GetMapping(value = "/shops/pcategories",produces = "application/json; charset=UTF-8")
    public ResultView getShopPcategory() throws Exception {
        return ResultUtil.returnSuccess(frontService.getShopsPcategories());
    }

    @GetMapping(value = "/shops",produces = "application/json; charset=UTF-8")
    public ResultView getShops(Shop shopForm) throws Exception {
        return ResultUtil.returnSuccess(frontService.getShops(shopForm));
    }

    @GetMapping(value = "/shop",produces = "application/json; charset=UTF-8")
    public ResultView getShop(@RequestParam("shopId")Long shopId) throws Exception {
        return ResultUtil.returnSuccess(frontService.getShop(shopId));
    }

    @GetMapping(value = "/shop/comments",produces = "application/json; charset=UTF-8")
    public ResultView getShopProduct(ShopComment shopCommentForm) throws Exception {
        return ResultUtil.returnSuccess(frontService.getShopComments(shopCommentForm));
    }

    @GetMapping(value = "/products",produces = "application/json; charset=UTF-8")
    public ResultView getProducts(Product productForm) throws Exception {
        return ResultUtil.returnSuccess(frontService.getProducts(productForm));
    }

    @GetMapping(value = "/product",produces = "application/json; charset=UTF-8")
    public ResultView getProduct(@RequestParam("productId") Long productId) throws Exception {
        return ResultUtil.returnSuccess(frontService.getProduct(productId));
    }

}
