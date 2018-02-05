package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.PgCartItem;
import cn.haizhi.market.main.bean.madao.PgCartItemDTO;
import cn.haizhi.market.main.bean.madao.PgCartItemExample;
import cn.haizhi.market.main.bean.richard.GroupProduct;
import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.mapper.madao.CommonMapper;
import cn.haizhi.market.main.mapper.madao.PgCartItemMapper;
import cn.haizhi.market.main.mapper.richard.GroupProductMapper;
import cn.haizhi.market.other.enums.madao.ErrorEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.form.madao.CartItemDeleteForm;
import cn.haizhi.market.other.form.madao.CartItemEmptyForm;
import cn.haizhi.market.other.form.madao.CartItemReviseForm;
import cn.haizhi.market.other.form.madao.PgCartItemForm;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PgCartItemService {
   @Autowired
    private PgCartItemMapper pgCartItemMapper;

   @Autowired
   private CommonMapper commonMapper;

   @Autowired
   private GroupProductMapper groupProductMapper;

   public String addCartItem(PgCartItemForm pgCartItemForm){
       //根据传入的productId判断该商品是否存在
       GroupProduct groupProduct = groupProductMapper.selectByPrimaryKey(pgCartItemForm.getProductId());
       if(groupProduct==null)
           throw new MadaoException(ErrorEnum.PRODUCT_NOT_EXIST, IdResultMap.getIdMap(pgCartItemForm.getProductId()));
       //判断该用户的购物车是否已经有该商品
       PgCartItemExample example = new PgCartItemExample();
       PgCartItemExample.Criteria criteria = example.createCriteria();
       criteria.andProductIdEqualTo(pgCartItemForm.getProductId());
       criteria.andUserIdEqualTo(pgCartItemForm.getUserId());
       List<PgCartItem> pgCartItemList = pgCartItemMapper.selectByExample(example);
       PgCartItem pgCartItem = null;
       //如果已存在该商品，加上数量，如果不存在，新建购物车项
       if(pgCartItemList.size()!=0) {
           pgCartItem = pgCartItemList.get(0);
           pgCartItem.setProductQuantity(pgCartItem.getProductQuantity() + pgCartItemForm.getProductQuantity());
           int result = pgCartItemMapper.updateByPrimaryKeySelective(pgCartItem);
           if(result<=0)
               throw new MadaoException(ErrorEnum.OPERATION_FAIL);
           return pgCartItem.getItemId();
       }else {
           pgCartItem = new PgCartItem();
           BeanUtils.copyProperties(pgCartItemForm, pgCartItem);
           pgCartItem.setItemId(KeyUtil.genUniquKey());
           int result = pgCartItemMapper.insert(pgCartItem);
           if(result<=0)
               throw new MadaoException(ErrorEnum.OPERATION_FAIL);
           return pgCartItem.getItemId();
       }
   }

   public List<PgCartItemDTO> getPgCartItemList(Long userId){
       List<PgCartItemDTO> pgCartItemDTOList = commonMapper.getPgCartItemDTOListByUserId(userId);
       return pgCartItemDTOList;
   }

   public void setPgCartItemQuantity(CartItemReviseForm cartItemReviseForm){
       PgCartItem pgCartItem = pgCartItemMapper.selectByPrimaryKey(cartItemReviseForm.getCartItemId());
       if(pgCartItem==null)
           throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(cartItemReviseForm.getCartItemId()));
       if(!pgCartItem.getUserId().equals(cartItemReviseForm.getUserId())){
           throw new MadaoException(ErrorEnum.CARTITEM_OWNER_ERROR, IdResultMap.getIdMap(cartItemReviseForm.getCartItemId()));
       }
       pgCartItem.setProductQuantity(cartItemReviseForm.getQuantity());
       int result =  pgCartItemMapper.updateByPrimaryKeySelective(pgCartItem);
       if(result<=0)
           throw new MadaoException(ErrorEnum.OPERATION_FAIL);
   }

   public void deleteCartItemById(CartItemDeleteForm cartItemDeleteForm){
       PgCartItem pgCartItem = pgCartItemMapper.selectByPrimaryKey(cartItemDeleteForm.getCartItemId());
       if(pgCartItem==null)
           throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(cartItemDeleteForm.getCartItemId()));
       if(!pgCartItem.getUserId().equals(cartItemDeleteForm.getUserId())){
           throw new MadaoException(ErrorEnum.CARTITEM_OWNER_ERROR, IdResultMap.getIdMap(cartItemDeleteForm.getCartItemId()));
       }
       int result = pgCartItemMapper.deleteByPrimaryKey(pgCartItem.getItemId());
       if(result<=0)
           throw new MadaoException(ErrorEnum.OPERATION_FAIL);
   }

   public void deleteCartItemByUserId(CartItemEmptyForm cartItemEmptyForm){
       PgCartItemExample example = new PgCartItemExample();
       PgCartItemExample.Criteria criteria = example.createCriteria();
       criteria.andUserIdEqualTo(cartItemEmptyForm.getUserId());
       int result = pgCartItemMapper.deleteByExample(example);
       if(result<=0)
           throw new MadaoException(ErrorEnum.OPERATION_FAIL);
   }



}
