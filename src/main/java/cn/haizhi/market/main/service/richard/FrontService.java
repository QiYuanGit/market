package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.*;
import cn.haizhi.market.main.view.PageView;
import cn.haizhi.market.main.view.richard.*;
import cn.haizhi.market.other.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Service
public class FrontService {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupProductService groupProductService;

    @Autowired
    private GroupProductPictureService groupProductPictureService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopPictureService shopPictureService;

    @Autowired
    private ShopCommentService shopCommentService;

    //查询批量商品分类，接收当前页码、每页条数
    //首页
    public Map<String, Object> getProductCategories(ProductCategory productCategoryForm) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<ProductCategory> productCategoryList = productCategoryService.selectLot(productCategoryForm);
        if(BeanUtil.notEmpty(productCategoryList)){
            dataMap.put("productCategories",new PageView(productCategoryList));
        }
        return dataMap;
    }

    //查询批量商品，接收当前页码、每页条数、商店编号、商品类别编号、商品类别名称
    //商品列表页面,商店商品列表页面，搜索商品页面
    public Map<String, Object> getProducts(Product productForm,String pcategoryName) throws Exception{
        Map<String, Object> dataMap = new LinkedHashMap<>();
        if(BeanUtil.notEmpty(pcategoryName)){
            ProductCategory productCategoryForm = new ProductCategory();
            productCategoryForm.setCategoryName(pcategoryName);
            List<ProductCategory> productCategoryList = productCategoryService.selectLot(productCategoryForm);
            if(BeanUtil.notEmpty(productCategoryList)){
                List<Long> pcategoryIdList = productCategoryList.stream().map(ProductCategory::getCategoryId).collect(Collectors.toList());
                productForm.setIdList(pcategoryIdList);
            }
        }
        List<Product> productList = productService.selectLot(productForm);
        if(BeanUtil.notEmpty(productList)){
            List<ProductView> productViewList = new ArrayList<>();
            for(Product product : productList){
                ProductView productView = new ProductView();
                BeanUtil.copyBean(product,productView);
                productViewList.add(productView);
            }
            dataMap.put("products",new PageView(productViewList));
        }
        return dataMap;
    }

    //查询单一商品，接收商品编号
    //商品详情页面
    public Map<String,Object> getProduct(Long productId){
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Product product = productService.selectOne(productId);
        if(BeanUtil.notNull(product)){
            ProductView productView = new ProductView();
            BeanUtil.copyBean(product,productView);
            dataMap.put("product",productView);
        }
        return dataMap;
    }

    //查询批量拼购商品，接受当前页码，每页条数，商品名称，分类编号
    public Map<String,Object> getGroupProducts(GroupProduct groupProductForm) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<GroupProduct> groupProductList = groupProductService.selectLot(groupProductForm);
        if(BeanUtil.notEmpty(groupProductList)){
            List<Long> productIdList = groupProductList.stream().map(GroupProduct::getProductId).collect(Collectors.toList());
            GroupProductPicture groupProductPictureForm = new GroupProductPicture();
            groupProductPictureForm.setIdList(productIdList);
            List<GroupProductPicture> groupProductPictureList = groupProductPictureService.selectLot(groupProductPictureForm);
            List<GroupProductView> groupProductViewList = new ArrayList<>();
            for(GroupProduct groupProduct : groupProductList){
                GroupProductView groupProductView = new GroupProductView();
                BeanUtil.copyBean(groupProduct,groupProductView);
                for(GroupProductPicture groupProductPicture : groupProductPictureList){
                   if(groupProductPicture.getProductId().equals(groupProduct.getProductId())){
                       GroupProductPictureView groupProductPictureView = new GroupProductPictureView();
                       BeanUtil.copyBean(groupProductPicture,groupProductPictureView);
                       groupProductView.addPicture(groupProductPictureView);
                   }
                }
                groupProductViewList.add(groupProductView);
            }
            dataMap.put("groupProducts",new PageView(groupProductViewList));
        }
        return dataMap;
    }

    //查询单一拼购商品，接受商品编号
    public Map<String,Object> getGroupProduct(Long productId) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        GroupProduct groupProduct = groupProductService.selectOne(productId);
        if(BeanUtil.notNull(groupProduct)){
            GroupProductView groupProductView = new GroupProductView();
            BeanUtil.copyBean(groupProduct,groupProductView);
            GroupProductPicture groupProductPictureForm = new GroupProductPicture();
            groupProductPictureForm.setProductId(productId);
            List<GroupProductPicture> groupProductPictureList = groupProductPictureService.selectLot(groupProductPictureForm);
            for(GroupProductPicture groupProductPicture : groupProductPictureList){
                if(groupProductPicture.getProductId().equals(groupProduct.getProductId())){
                    GroupProductPictureView groupProductPictureView = new GroupProductPictureView();
                    BeanUtil.copyBean(groupProductPicture,groupProductPictureView);
                    groupProductView.addPicture(groupProductPictureView);
                }
            }
            dataMap.put("groupProduct",groupProductView);
        }
        return dataMap;
    }


    //查询批量商店，接收当前页码、每页条数、是否推荐、排列顺序
    //首页，商店列表页面
    public Map<String, Object> getShops(Shop shopForm) throws Exception{
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<Shop> shopList = shopService.selectLot(shopForm);
        if(BeanUtil.notEmpty(shopList)){
            //查询批量商店图片
            List<Long> shopIdList = shopList.stream().map(Shop::getShopId).collect(Collectors.toList());
            ShopPicture shopPictureForm = new ShopPicture();
            shopPictureForm.setIdList(shopIdList);
            List<ShopPicture> shopPictureList = shopPictureService.selectLog(shopPictureForm);
            List<ShopView> shopViewList = new ArrayList<>();
            for(Shop shop : shopList){
                ShopView shopView = new ShopView();
                BeanUtil.copyBean(shop,shopView);
                for(ShopPicture shopPicture : shopPictureList){
                    if(shop.getShopId().equals(shopPicture.getShopId())){
                        ShopPictureView shopPictureView = new ShopPictureView();
                        BeanUtil.copyBean(shopPicture,shopPictureView);
                        shopView.addPicture(shopPictureView);
                    }
                }
                shopViewList.add(shopView);
            }
            dataMap.put("shops", new PageView(shopViewList));
        }
        return dataMap;
    }

    //查询单一商店,接收商店编号
    //商店详情页面
    public Map<String, Object> getShop(Long shopId) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Shop shop = shopService.selectOne(shopId);
        if(BeanUtil.notNull(shop)){
            ShopPicture shopPictureForm = new ShopPicture();
            shopPictureForm.setShopId(shopId);
            List<ShopPicture> shopPictureList = shopPictureService.selectLog(shopPictureForm);
            ShopView shopView = new ShopView();
            BeanUtil.copyBean(shop,shopView);
            for(ShopPicture shopPicture : shopPictureList){
                ShopPictureView shopPictureView = new ShopPictureView();
                BeanUtil.copyBean(shopPicture,shopPictureView);
                shopView.addPicture(shopPictureView);
            }
            dataMap.put("shop",shopView);
        }
        return dataMap;
    }

    //查询批量商店评论，接收商店编号、当前页码、每页条数
    //商店详情页面
    public Map<String,Object> getShopComments(ShopComment shopCommentForm) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        List<ShopCommentView> shopCommentViewList = shopCommentService.getallWithJoin(shopCommentForm);
        if(BeanUtil.notEmpty(shopCommentViewList)){
            dataMap.put("shopComments",new PageView(shopCommentViewList));
        }
        return dataMap;
    }

}
