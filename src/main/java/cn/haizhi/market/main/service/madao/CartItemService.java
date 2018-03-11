package cn.haizhi.market.main.service.madao;


import cn.haizhi.market.main.bean.madao.*;
import cn.haizhi.market.main.mapper.madao.CartItemMapper;
import cn.haizhi.market.main.mapper.madao.CartShopMapper;
import cn.haizhi.market.main.mapper.madao.CommonMapper;
import cn.haizhi.market.main.mapper.qiyuan.UserMapper;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.form.madao.CartItemForm;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CartItemService {
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    CartShopMapper cartShopMapper;
    //添加进购物车
    public String addCartItem(CartItemForm cartItemForm){
       //通过商品id查询商品和店铺信息，验证是否存在
        ProductShop productShop = commonMapper.getShopInfoByProductId(cartItemForm.getProductId());
        if(productShop==null || productShop.getShopId()==null) {
            throw new MadaoException(ErrorEnum.PRODUCT_INFO_ERROR, IdResultMap.getIdMap(cartItemForm.getProductId()));
        }
        if(productShop.getProductStock()<cartItemForm.getProductQuantity()){
            throw new MadaoException(ErrorEnum.PRODUCT_STOCK_ERROR, IdResultMap.getIdMap(cartItemForm.getProductId()));
        }

        //判断该项商品是否已在购物车，如果是的话，更新数量，如果不是的话，新建项
        List<CartItem> cartItemList = checkItemExist(cartItemForm.getUserId(), cartItemForm.getProductId());
        if(cartItemList.size()>0){
            CartItem cartItem = cartItemList.get(0);
            cartItem.setProductQuantity(cartItemForm.getProductQuantity());
            int result = cartItemMapper.updateByPrimaryKeySelective(cartItem);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
            return cartItem.getItemId();
        }

        String itemId = KeyUtil.genUniquKey();
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartItemForm, cartItem);
        cartItem.setItemId(itemId);
        cartItem.setShopId(productShop.getShopId());
        cartItem.setShopName(productShop.getShopName());

        //检查用户购物车项中是否有该商铺，没有就新建一条记录
        List<CartShop> cartShopList = checkCartShop(cartItemForm.getUserId(), productShop.getShopId());
        if(cartShopList.size()==0 || cartShopList==null){
            CartShop cartShop = new CartShop();
            cartShop.setCartId(KeyUtil.genUniquKey());
            cartShop.setShopId(productShop.getShopId());
            cartShop.setUserId(cartItemForm.getUserId());
            int result = cartShopMapper.insertSelective(cartShop);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
            cartItem.setCartId(cartShop.getCartId());
        }else{
            cartItem.setCartId(cartShopList.get(0).getCartId());
        }
        log.info("【添加进购物车】 cartItem={}, shopId", cartItem, productShop.getShopId());
        int result = cartItemMapper.insertSelective(cartItem);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        return cartItem.getItemId();
}



    //根据用户id获取普通购物车项列表并分页
        public PageInfo<CartShopDTO> getCartListByUserIdInPage(Long userId, Integer pageNum, Integer pageSize){
            //获取购物车商铺列表 放在前面，分页要用到
            PageHelper.startPage(pageNum, pageSize);
            List<CartShopDTO> cartShopList = commonMapper.getCartShopDTOByUserId(userId);
            PageInfo pageInfo = new PageInfo(cartShopList);
            //获取购物项列表
            List<CartItemDTO> cartItemDTOList = commonMapper.getCartItemDTOByUserId(userId);
            //进行拼装
            //id-购物车商铺键值对
            Map<Long, CartShopDTO> map = new HashMap<>();
            for(CartShopDTO cartShopDTO: cartShopList){
                cartShopDTO.setCartItemDTOList(new ArrayList<CartItemDTO>());
                map.put(cartShopDTO.getShopId(), cartShopDTO);
            }
            //循环将购物车项加进购物车商铺
            for(CartItemDTO cartItemDTO: cartItemDTOList){
                CartShopDTO cartShopDTO = map.get(cartItemDTO.getShopId());
                if(cartShopDTO!=null){
                    cartShopDTO.getCartItemDTOList().add(cartItemDTO);
                }
            }
            pageInfo.setList(new ArrayList<>(map.values()));
            return pageInfo;

        }


    //根据id获取普通购物车项列表
    public List<CartShopDTO> getCartListByUserId(Long userId){
        //获取购物车商铺列表
        List<CartShopDTO> cartShopList = commonMapper.getCartShopDTOByUserId(userId);
        //获取购物项列表
        List<CartItemDTO> cartItemDTOList = commonMapper.getCartItemDTOByUserId(userId);
        //进行拼装
        Map<Long, CartShopDTO> map = new HashMap<>();
        for(CartShopDTO cartShopDTO: cartShopList){
            cartShopDTO.setCartItemDTOList(new ArrayList<CartItemDTO>());
            map.put(cartShopDTO.getShopId(), cartShopDTO);
        }
        for(CartItemDTO cartItemDTO: cartItemDTOList){
            CartShopDTO cartShopDTO = map.get(cartItemDTO.getShopId());
            if(cartShopDTO!=null){
                cartShopDTO.getCartItemDTOList().add(cartItemDTO);
            }
        }
        return new ArrayList<>(map.values());
    }

    //根据id删除购物车项
    public void deleteCartItemById(Long userId, String cartItemId){
        CartItem cartItem = cartItemMapper.selectByPrimaryKey(cartItemId);
        if(cartItem==null)
            throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(cartItemId));
        if(!cartItem.getUserId().equals(userId))
            throw new MadaoException(ErrorEnum.CARTITEM_OWNER_ERROR, IdResultMap.getIdMap(cartItemId));

        int result = cartItemMapper.deleteByPrimaryKey(cartItemId);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        //判断删除后该商店是否还有商品在，没有就把商店也删除掉
        if(checkCartShopItem(userId, cartItem.getShopId())==0){
            CartShopExample example = new CartShopExample();
            CartShopExample.Criteria criteria = example.createCriteria();
            criteria.andShopIdEqualTo(cartItem.getShopId());
            result = cartShopMapper.deleteByExample(example);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
    }

    //根据用户id删除购物车项,即 清空该用户的购物车
    public void deleteCartItemByUserId(Long userId){
        //删除所有购物车商铺
        CartShopExample cartShopExample = new CartShopExample();
        CartShopExample.Criteria criteria1 = cartShopExample.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        cartShopMapper.deleteByExample(cartShopExample);

        //删除所有购物车项
        CartItemExample example = new CartItemExample();
        CartItemExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        int result =  cartItemMapper.deleteByExample(example);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
    }

    //更新购物车项数量，数量的规范控制在表单验证那里
    public void updateCartItemQuantity(Long userId, String itemId, int quantity){
        CartItem cartItem = cartItemMapper.selectByPrimaryKey(itemId);
        if(cartItem==null) {
            throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(itemId));
        }
        if(!userId.equals(cartItem.getUserId()))
            throw new MadaoException(ErrorEnum.CARTITEM_OWNER_ERROR, IdResultMap.getIdMap(itemId));
        cartItem.setProductQuantity(quantity);
        int result = cartItemMapper.updateByPrimaryKeySelective(cartItem);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
    }

    //获取指定用户id和商品id的购物项，用来检查该商品是否已经在购物车中
    public List<CartItem> checkItemExist(Long userId, Long productId){
        CartItemExample example = new CartItemExample();
        CartItemExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andUserIdEqualTo(userId);
        List<CartItem> cartItemList = cartItemMapper.selectByExample(example);
        return cartItemList;
    }

    //根据用户名和商铺名查购物车类组,用来查看是否已存在该商铺
    public List<CartShop> checkCartShop(Long userId, Long shopId){
        CartShopExample example =  new CartShopExample();
        CartShopExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andUserIdEqualTo(userId);
        return cartShopMapper.selectByExample(example);
    }

    //判断购物车中某商铺的项数
    public Long checkCartShopItem(Long userId, Long shopId){
        CartItemExample example = new CartItemExample();
        CartItemExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andShopIdEqualTo(shopId);
        Long count = cartItemMapper.countByExample(example);
        return count;
    }
}