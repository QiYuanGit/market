package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.bean.richard.ProductCategory;
import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.bean.richard.ShopComment;
import cn.haizhi.market.main.view.PageView;
import cn.haizhi.market.main.view.richard.ProductView;
import cn.haizhi.market.main.view.richard.ShopCommentView;
import cn.haizhi.market.main.view.richard.ShopView;
import cn.haizhi.market.other.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Service
public class FrontService {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopCommentService shopCommentService;

    public Map<String, Object> getShopsPcategories() throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        //查询商品分类
        ProductCategory productCategoryForm = new ProductCategory();
        productCategoryForm.setPageNum(1);
        productCategoryForm.setPageSize(8);
        List<ProductCategory> productCategories = productCategoryService.getall(productCategoryForm);
        dataMap.put("productCategories",productCategories );
        //查询推荐商店
        Shop shopForm = new Shop();
        shopForm.setPageNum(1);
        shopForm.setPageSize(10);
        shopForm.setIsRecom(true);
        List<ShopView> shopViewList = shopService.getallWithJoin(shopForm);
        dataMap.put("recommendShops", new PageView(shopViewList));
        return dataMap;
    }

    //查询全部商店，接收当前页码、每页条数
    public Map<String, Object> getShops(Shop shopForm) throws Exception{
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<ShopView> shopViewList = shopService.getallWithJoin(shopForm);
        dataMap.put("shops", new PageView(shopViewList));
        return dataMap;
    }

    //查询单个商店,接收商店编号
    public Map<String, Object> getShop(Long shopId) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        ShopView shopView = shopService.getoneWithJoin(shopId);
        dataMap.put("shop",shopView);
        return dataMap;
    }

    //查询全部商店评论，接收商店编号、当前页码、每页条数
    public Map<String,Object> getShopComments(ShopComment shopCommentForm) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<ShopCommentView> shopCommentViewList = shopCommentService.getallWithJoin(shopCommentForm);
        dataMap.put("shopComments",new PageView(shopCommentViewList));
        return dataMap;
    }

    //查询全部商品，接收当前页码、每页条数、商店编号、商品类别编号
    //商品列表,搜索商品，商店商品列表
    public Map<String, Object> getProducts(Product productForm) throws Exception{
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<ProductView> productViews = new ArrayList<>();
        List<Product> products = productService.getall(productForm);
        for(Product product : products){
            ProductView productView = new ProductView();
            BeanUtil.copyBean(product,productView);
            productViews.add(productView);
        }
        dataMap.put("products",new PageView(productViews));
        return dataMap;
    }

    //查询单个商品，接收商品编号
    public Map<String,Object> getProduct(Long productId){
        Map<String, Object> dataMap = new LinkedHashMap<>();
        ProductView productView = new ProductView();
        Product product = productService.getone(productId);
        BeanUtil.copyBean(product,productView);
        dataMap.put("product",productView);
        return dataMap;
    }

}
