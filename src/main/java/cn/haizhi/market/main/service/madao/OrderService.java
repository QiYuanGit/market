package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.*;
import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.mapper.madao.*;
import cn.haizhi.market.main.mapper.richard.ProductMapper;
import cn.haizhi.market.other.enums.madao.*;
import cn.haizhi.market.other.enums.richard.ShopStateEnum;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.form.madao.OrderCreateForm;
import cn.haizhi.market.other.util.IdResultMap;
import cn.haizhi.market.other.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static cn.haizhi.market.other.util.ErrorResultUtil.getResultViewList;
import static cn.haizhi.market.other.util.ErrorResultUtil.getResultViewListWithName;

@Service
@Transactional
@Slf4j
public class OrderService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    PgOrderMasterMapper pgOrderMasterMapper;

    @Autowired
    ProductMapper productMapper;

    //添加拼购订单
    public PgOrderDTO addPgOrder(OrderCreateForm orderForm) {
        //用于返回生成的订单
        PgOrderDTO pgOrderDTO = new PgOrderDTO();
        pgOrderDTO.setOrderItemList(new ArrayList<>());
        UserAddress address = commonMapper.getUserAddressById(orderForm.getAddressId());
        //检查地址是否正确
        if (address==null || address.getUserId() != orderForm.getUserId()) {
            log.error("用户地址信息错误");
            throw new MadaoException(ErrorEnum.ADDRESS_ERROR, orderForm.getAddressId());
        }
        //获取该订单的所有购物车项
        List<CartItemDTO> pgCartItemDTOList = commonMapper.getCartItemDTOByCartItemIdList(orderForm.getCartItemIdList());

        //如果查出的购物项数量和传入表单的数量不一致，获取查不出的项的id抛出异常
        if(!(pgCartItemDTOList.size()==orderForm.getCartItemIdList().size())){
            List<String> id = new LinkedList<>();
            id.addAll(orderForm.getCartItemIdList());
            for(CartItemDTO cartItemDTO: pgCartItemDTOList){
                id.remove(cartItemDTO.getItemId());
            }
            throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(id));
        }

        //处理拼购订单
        String orderId = KeyUtil.genUniquKey();
        BigDecimal orderAmount = BigDecimal.ZERO;

        ArrayList<String> id = new ArrayList<>();
        for (CartItemDTO cartItemDTO : pgCartItemDTOList) {
            //记录下不是拼购购物项的id
            if(!cartItemDTO.getItemCategory().equals(CartItemCategoryEnum.GROUP_ITEM.getCode())){
                log.error("【Error】 = {}", ErrorEnum.CARTITEM_CATEGORY_ERROR.getMessage());
                id.add(cartItemDTO.getItemId());
            }
            orderAmount = orderAmount.add(cartItemDTO.getDiscountPrice().multiply(BigDecimal.valueOf(cartItemDTO.getProductQuantity())));
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(cartItemDTO, orderItem);
            orderItem.setItemId(KeyUtil.genUniquKey());
            orderItem.setProductPrice(cartItemDTO.getDiscountPrice());
            orderItem.setOrderId(orderId);
            orderItemMapper.insertSelective(orderItem);
            pgOrderDTO.getOrderItemList().add(orderItem);
        }

        if(id.size()>0) {
            throw new MadaoException(ErrorEnum.CARTITEM_NO_PG, IdResultMap.getIdMap(id));
        }

        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        pgOrderMaster.setUserId(orderForm.getUserId());
        pgOrderMaster.setUserName(orderForm.getUserName());
        pgOrderMaster.setUserPhone(address.getPhone());
        pgOrderMaster.setUserAddress(address.getUserAddress());
        pgOrderMaster.setOrderAmount(orderAmount);
        pgOrderMaster.setOrderId(orderId);

        pgOrderMasterMapper.insertSelective(pgOrderMaster);
        BeanUtils.copyProperties(pgOrderMaster, pgOrderDTO);
        decreaseStock(pgCartItemDTOList, ProductTypeEnum.PRODUCT_PG.getCode());
        return pgOrderDTO;
    }

    //添加普通订单
    public List<OrderDTO> addOrder(OrderCreateForm orderForm) {
        UserAddress address = commonMapper.getUserAddressById(orderForm.getAddressId());
        if (address==null || address.getUserId() != orderForm.getUserId()) {
            log.error("用户地址信息错误");
            throw new MadaoException(ErrorEnum.ADDRESS_ERROR, IdResultMap.getIdMap(orderForm.getAddressId()));
        }

        //获取该订单的所有购物车项
        List<CartItemDTO> cartItemDTOList = commonMapper.getCartItemDTOByCartItemIdList(orderForm.getCartItemIdList());

        //如果从订单表中查出的数据数量和传入的id数量不符合，说明传入的id不正确，抛出异常并返回查不出的项的id
        if(orderForm.getCartItemIdList().size()!=cartItemDTOList.size()){
           List<String> id = new LinkedList<>();
           id.addAll(orderForm.getCartItemIdList());
            for(CartItemDTO cartItemDTO: cartItemDTOList){
                id.remove(cartItemDTO.getItemId());
            }
            throw new MadaoException(ErrorEnum.CARTITEM_NOT_EXIST, IdResultMap.getIdMap(id));
        }

        //得到 商铺id--购物项列表的键值对
        Map<Long, List<CartItemDTO>> shopIdCartItemDTOMap = new HashMap<>();
        List<String> id = new ArrayList<>();
        for (CartItemDTO cartItemDTO : cartItemDTOList) {
            //判断该购物车项是否属于普通商品购物车项
            if(!cartItemDTO.getItemCategory().equals(CartItemCategoryEnum.PLAIN_ITEM.getCode())){
                id.add(cartItemDTO.getItemId());
            }
            //根据商铺id加入map
            Long shopId = cartItemDTO.getShopId();
            if (!shopIdCartItemDTOMap.containsKey(shopId)) {
                shopIdCartItemDTOMap.put(shopId, new ArrayList<>());
            }
            shopIdCartItemDTOMap.get(shopId).add(cartItemDTO);
        }
        //购物车项不属于普通商品就抛出异常，并放回错误的id列表
        if(id.size()>0){
            throw new MadaoException(ErrorEnum.CARTITEM_NOT_PLAIN, IdResultMap.getIdMap(id));
        }

        List<OrderDTO> orderDTOList = new ArrayList<>();
        //处理普通商品订单
        if(shopIdCartItemDTOMap.size()>0){
            //循环获取每个商家id对应的购物车项
            ArrayList<CartItemDTO> listAll = new ArrayList<>();
            //记录下不符合商店最低配送价格的购物项id列表和商店状态表
//            List<Long> limitPriceIdList = new ArrayList<>();
            Map<ErrorEnum, List<String>> map = new HashMap<>();
            for(Map.Entry entry: shopIdCartItemDTOMap.entrySet()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderItemList(new ArrayList<>());
                List<CartItemDTO> list = (List<CartItemDTO>) entry.getValue();
                listAll.addAll(list);
                String orderId = KeyUtil.genUniquKey();
                BigDecimal productAmount = BigDecimal.ZERO;
                Long shopId = list.get(0).getShopId();
                String shopName = list.get(0).getShopName();
                //获得该商店配送的基本信息
                ShopPriceInfo shopPriceInfo = commonMapper.getShopPriceInfo(shopId);
                //如果商店是关闭状态，加入异常
                boolean flag = false;
                if(shopPriceInfo.getShopState().byteValue()==ShopStateEnum.IS_CLOSED.getCode().byteValue()){
                    flag = true;
                    if(!map.containsKey(ErrorEnum.SHOP_CLOSE)){
                        map.put(ErrorEnum.SHOP_CLOSE, new ArrayList<>());
                    }
                    map.get(ErrorEnum.SHOP_CLOSE).add(new Long(shopPriceInfo.getShopId()).toString());
                }

                //如果达不到最低的配送价格，就加入异常
                if(shopPriceInfo.getLimitPrice().compareTo(productAmount)>0){
                    flag = true;
                    if(!map.containsKey(ErrorEnum.ORDER_LIMIT_ERROR)){
                        map.put(ErrorEnum.ORDER_LIMIT_ERROR, new ArrayList<>());
                    }
                    map.get(ErrorEnum.ORDER_LIMIT_ERROR).add(String.valueOf((Long)entry.getKey()));
                }
                if(flag)
                    continue;

                //循环每个购物车项，转为订单项，插入数据库并组装成一个订单主项
                for(CartItemDTO cartItemDTO: list){
                    productAmount = productAmount.add(cartItemDTO.getProductPrice().multiply(BigDecimal.valueOf(cartItemDTO.getProductQuantity())));
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(cartItemDTO, orderItem);
                    orderItem.setItemId(KeyUtil.genUniquKey());
                    orderItem.setProductPrice(cartItemDTO.getProductPrice());
                    orderItem.setOrderId(orderId);
                    orderItemMapper.insertSelective(orderItem);
                    orderDTO.getOrderItemList().add(orderItem);
                }



                OrderMaster orderMaster = new OrderMaster();
                orderMaster.setShopId(shopId);
                orderMaster.setShopName(shopName);
                orderMaster.setUserId(orderForm.getUserId());
                orderMaster.setUserName(orderForm.getUserName());
                orderMaster.setUserPhone(address.getPhone());
                orderMaster.setUserAddress(address.getUserAddress());
                orderMaster.setProductAmount(productAmount);
                orderMaster.setSendPrice(shopPriceInfo.getSendPrice());
                orderMaster.setOrderAmount(productAmount.add(shopPriceInfo.getSendPrice()));
                orderMaster.setOrderId(orderId);
                orderMasterMapper.insertSelective(orderMaster);
                BeanUtils.copyProperties(orderMaster, orderDTO);
                orderDTOList.add(orderDTO);
            }

            if(map.size()>0){
                throw new MadaoException(ErrorEnum.ORDER_CREATE_ERROR, getResultViewListWithName("shopId", map));
            }
            //扣库存
            decreaseStock(listAll, ProductTypeEnum.PRODUCT_PLAIN.getCode());
        }
        return orderDTOList;
    }


    //根据购物车项列表扣库存
    public void decreaseStock(List<CartItemDTO> cartItemDTOList, int productCategory){
        //存放扣库存时错误的商品的  错误信息---购物项 键值对
        Map<ErrorEnum, List<String>> map = new HashMap<>();

        for(CartItemDTO cartItemDTO: cartItemDTOList){
            Product product = productMapper.selectByPrimaryKey(cartItemDTO.getProductId());
            if(product==null) {
                if (!map.containsKey(ErrorEnum.PRODUCT_NOT_EXIST)) {
                    map.put(ErrorEnum.PRODUCT_NOT_EXIST, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_NOT_EXIST).add(cartItemDTO.getItemId());
            }
            if (product.getProductState()==0) {
                if(!map.containsKey(ErrorEnum.PRODUCT_DOWN)){
                    map.put(ErrorEnum.PRODUCT_DOWN, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_DOWN).add(cartItemDTO.getItemId());
            }
            if(!product.getDiscountState().equals(productCategory)){
                if(!map.containsKey(ErrorEnum.PRODUCT_CATEGORY_ERROR)){
                    map.put(ErrorEnum.PRODUCT_CATEGORY_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_CATEGORY_ERROR).add(cartItemDTO.getItemId());
            }
            Integer result = product.getProductStock() - cartItemDTO.getProductQuantity();
            if(result<0) {
                log.error("【商品库存不足】-------Need={}---------product={}", cartItemDTO.getProductQuantity(), product);
                if(!map.containsKey(ErrorEnum.PRODUCT_STOCK_ERROR)){
                    map.put(ErrorEnum.PRODUCT_STOCK_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_STOCK_ERROR).add(cartItemDTO.getItemId());
            }

            product.setProductStock(result);
            log.info("【扣库存成功】------------Need={}-----------product={}", cartItemDTO.getProductQuantity(), product);
            productMapper.updateByPrimaryKey(product);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_CREATE_ERROR, getResultViewList(map));
        }
    }



//    public List<OrderDTO> getOrderByUserId(Long userId, Byte orderStatus){
//        Map<String, OrderDTO> map = new HashMap<>();
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        OrderDTO orderDTO = null;
//        OrderMasterExample example = new OrderMasterExample();
//        OrderMasterExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(userId);
//        if(orderStatus!=null){
//            criteria.andOrderStatusEqualTo(orderStatus);
//        }
//        List<OrderMaster> orderMasterList = orderMasterMapper.selectByExample(example);
//
//        //获取订单id列表
//        List<String> orderMasterIdList = new ArrayList<>();
//        for(OrderMaster orderMaster: orderMasterList){
//            orderDTO = new OrderDTO();
//            orderDTO.setOrderItemList(new ArrayList<>());
//            BeanUtils.copyProperties(orderMaster, orderDTO);
//            map.put(orderMaster.getOrderId(), orderDTO);
//            orderMasterIdList.add(orderMaster.getOrderId());
//            orderDTOList.add(orderDTO);
//        }


//        //获订单项列表
//        OrderItemExample orderItemExample = new OrderItemExample();
//        OrderItemExample.Criteria criteria1 = orderItemExample.createCriteria();
//        criteria.andOrderIdIn(orderMasterIdList);
//        List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
//        //拼装
//
//        for(OrderItem orderItem: orderItemList){
//            map.get(orderItem.getOrderId()).getOrderItemList().add(orderItem);
//        }
//
//
//
//        return orderDTOList;
//    }

    public List<OrderDTO> getOrderDTO(Long shopId, Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus){
        return commonMapper.getOrderDTOByUserId(shopId, userId, orderStatus, payStatus, deliveryStatus, commentStatus);

    }

    public List<PgOrderDTO> getPgOrderDTO(Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus){
        return commonMapper.getPgOrderByUserId(userId, orderStatus, payStatus, deliveryStatus, commentStatus);
    }

    //用户支付普通订单
    public List<OrderDTO> payOrder(Long userId, List<String> orderIdList){
        List<OrderDTO> orderDTOList = getOrderDTObyIdList(orderIdList);
        BigDecimal amount = BigDecimal.ZERO;
        //Todo 批处理
        // 得出所有异常，一次返回
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(OrderDTO orderDTO: orderDTOList) {
            if (!orderDTO.getUserId().equals(userId)) {
                if (!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR)) {
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(orderDTO.getOrderId());
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage());
            }
            if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage());
                if (!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR)) {
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderDTO.getOrderId());
            }
            if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
                log.error("【异常】={}", ErrorEnum.ORDER_PAY_STATUS_ERROR.getMessage());
                if (!map.containsKey(ErrorEnum.ORDER_PAY_STATUS_ERROR)) {
                    map.put(ErrorEnum.ORDER_PAY_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_PAY_STATUS_ERROR).add(orderDTO.getOrderId());
            }

            amount = amount.add(orderDTO.getOrderAmount());
        }
        //如果有错误，抛出异常
        if(map.size()!=0){
            throw new MadaoException(ErrorEnum.ORDER_PAY_FAIL, getResultViewList(map));
        }

        //Todo 支付


        //更新订单
        OrderMaster orderMaster = new OrderMaster();
        for(OrderDTO orderDTO: orderDTOList){
            orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        }
        return orderDTOList;
    }

    //用户支付拼购订单
    public List<PgOrderDTO> payPgOrder(Long userId, List<String> orderIdList) {
        List<PgOrderDTO> pgOrderDTOList = getPgOrderDTObyIdList(orderIdList);
        BigDecimal amount = BigDecimal.ZERO;
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderDTO pgOrderDTO: pgOrderDTOList){
            if(!pgOrderDTO.getUserId().equals(userId)){
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(pgOrderDTO.getOrderId());
            }
            if(!pgOrderDTO.getOrderStatus().equals( PgOrderEnum.NEW.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR)){
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderDTO.getOrderId());
            }
            if(!pgOrderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_PAY_STATUS_ERROR)){
                    map.put(ErrorEnum.ORDER_PAY_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_PAY_STATUS_ERROR).add(pgOrderDTO.getOrderId());
            }
            amount = amount.add(pgOrderDTO.getOrderAmount());
        }

        if (map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_PAY_FAIL, getResultViewList(map));
        }

        //Todo 支付

        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        for(PgOrderDTO pgOrderDTO: pgOrderDTOList){
            pgOrderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
            BeanUtils.copyProperties(pgOrderDTO, pgOrderMaster);
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        return pgOrderDTOList;
    }

    //用户取消普通订单
    public List<OrderDTO> cancelOrderByUser(Long userId, List<String> orderIdList){
        List<OrderDTO> orderDTOList = getOrderDTObyIdList(orderIdList);
        OrderMaster orderMaster = new OrderMaster();
        List<OrderItem> orderItemList = new ArrayList<>();
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(OrderDTO orderDTO: orderDTOList ){
            if(!orderDTO.getUserId().equals(userId)){
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR))
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(orderDTO.getOrderId());
            }
            if(orderDTO.getOrderStatus().equals(OrderStatusEnum.TO_CANCEL.getCode()) || orderDTO.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderDTO.getOrderId());
            }
            //如果送达已经超过一天，不能退货
            if(orderDTO.getArriveTime()!=null && new Date().getTime() - orderDTO.getArriveTime().getTime()>86400000L){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_OVERDUE))
                    map.put(ErrorEnum.ORDER_HAD_OVERDUE, new ArrayList<>());
                map.get(ErrorEnum.ORDER_HAD_OVERDUE).add(orderDTO.getOrderId());
            }
            orderItemList.addAll(orderDTO.getOrderItemList());
            orderDTO.setOrderStatus(OrderStatusEnum.TO_CANCEL.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        }

        if(map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_CANCEL_FAIL, getResultViewList(map));
        }
        increaseStock(orderItemList);
        return orderDTOList;
    }

    //退单后加库存操作
    public void increaseStock(List<OrderItem> orderItemList){
        for(OrderItem orderItem: orderItemList){
            commonMapper.increaseStock(orderItem.getProductId(), orderItem.getProductQuantity());
            System.out.println("-----------------" + orderItem.getProductId() + "------------" + orderItem.getProductQuantity());
        }
    }

    //商家取消普通订单
    public List<OrderDTO> cancelOrderByShop(Long shopId, List<String>  orderIdList){
        List<OrderDTO> orderDTOList = getOrderDTObyIdList(orderIdList);
        List<OrderItem> orderItemList = new ArrayList<>();

        //异常检测
        OrderMaster orderMaster = new OrderMaster();
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(OrderDTO orderDTO: orderDTOList ){
            if(!orderDTO.getShopId().equals(shopId)){
                log.error("【异常】={}", ErrorEnum.ORDER_SHOP_OWNER_ERROR.getMessage() );
                if(!map.containsKey(ErrorEnum.ORDER_SHOP_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_SHOP_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_SHOP_OWNER_ERROR).add(orderDTO.getOrderId());
            }
            if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage() + orderDTO.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderDTO.getOrderId());
            }
//            if(orderDTO.getArriveTime().getTime()-new Date().getTime()>86400000L){
//                if(!map.containsKey(ErrorEnum.ORDER_HAD_OVERDUE))
//                    map.put(ErrorEnum.ORDER_HAD_OVERDUE, new ArrayList<>());
//                map.get(ErrorEnum.ORDER_HAD_OVERDUE).add(orderDTO.getOrderId());
//            }
            //如果已经送货完成，不能取消订单
            if(orderDTO.getDeliveryStatus().equals(DeliveryStatusEnum.FINSH.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_DELIVERY)){
                    map.put(ErrorEnum.ORDER_HAD_DELIVERY, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_HAD_DELIVERY).add(orderDTO.getOrderId());
            }
            orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            orderItemList.addAll(orderDTO.getOrderItemList());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

        }
        //发现异常则组装后抛出
        if (map.size()>0)
            throw new MadaoException(ErrorEnum.ORDER_CANCEL_FAIL, getResultViewList(map));

//        OrderMaster orderMaster = new OrderMaster();
//        for(OrderDTO orderDTO: orderDTOList){
//            if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
//                //Todo  退款
//
//                orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
//            }
//
//
//            BeanUtils.copyProperties(orderDTO, orderMaster);
//            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
//        }
        //加库存
        increaseStock(orderItemList);
        return orderDTOList;
    }

    //用户取消拼购订单
    public List<PgOrderDTO> cancelPgOrderByUser(Long userId, List<String> pgOrderIdList){
        List<PgOrderDTO> pgOrderDTOList = getPgOrderDTObyIdList(pgOrderIdList);
        List<OrderItem> orderItemList = new ArrayList<>();

        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderDTO pgOrderDTO: pgOrderDTOList){
            if(!userId.equals(pgOrderDTO.getUserId())){
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR))
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(pgOrderDTO.getOrderId());
            }
            if(pgOrderDTO.getOrderStatus().equals(PgOrderEnum.TO_CANCEL.getCode()) || pgOrderDTO.getOrderStatus().equals(PgOrderEnum.CANCEL.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderDTO.getOrderId());
            }
            //如果送达已经超过一天，不能退货
            if(pgOrderDTO.getArriveTime()!=null && new Date().getTime() - pgOrderDTO.getArriveTime().getTime()>86400000L){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_OVERDUE))
                    map.put(ErrorEnum.ORDER_HAD_OVERDUE, new ArrayList<>());
                map.get(ErrorEnum.ORDER_HAD_OVERDUE).add(pgOrderDTO.getOrderId());
            }
            orderItemList.addAll(pgOrderDTO.getOrderItemList());
            pgOrderDTO.setOrderStatus(OrderStatusEnum.TO_CANCEL.getCode());
            BeanUtils.copyProperties(pgOrderDTO, pgOrderMaster);
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.ORDER_CANCEL_FAIL, getResultViewList(map));
        //加库存
        increaseStock(orderItemList);
        return pgOrderDTOList;
    }

    //商家取消拼购订单
    public List<PgOrderDTO> cancelPgOrderByShop(List<String>  pgOrderIdList){
        List<PgOrderDTO> pgOrderDTOList = getPgOrderDTObyIdList(pgOrderIdList);
        List<OrderItem> orderItemList = new ArrayList<>();


        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderDTO pgOrderDTO: pgOrderDTOList){
            if(pgOrderDTO.getOrderStatus().equals(PgOrderEnum.CANCEL.getCode()) || pgOrderDTO.getOrderStatus().equals(PgOrderEnum.TO_CANCEL.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR, pgOrderDTO.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderDTO.getOrderId());
            }
            if(pgOrderDTO.getDeliveryStatus().equals(DeliveryStatusEnum.FINSH.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_DELIVERY)){
                    map.put(ErrorEnum.ORDER_HAD_DELIVERY, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_HAD_DELIVERY).add(pgOrderDTO.getOrderId());
            }
            orderItemList.addAll(pgOrderDTO.getOrderItemList());
            pgOrderDTO.setOrderStatus(PgOrderEnum.CANCEL.getCode());
            BeanUtils.copyProperties(pgOrderDTO, pgOrderMaster);
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR, getResultViewList(map));
        }
       //加库存
        increaseStock(orderItemList);
        return pgOrderDTOList;
    }


    //商家退款
    public void refund(Long shopId, List<String> orderIdList){
        //Todo
    }

    //商家设置配送时间
    public List<String> updateDeliveryTime(Long shopId, List<String> orderIdList, Long date1, Long date2){
        Date deliveryDate = (date1==null ? null: new Date(date1));
        Date arriveDate = (date2==null ? null : new Date(date2));
        if((deliveryDate!=null && deliveryDate.before(new Date())) || (arriveDate!=null && arriveDate.before(new Date()))){
            Map<String, String> map = new HashMap<>();
            map.put("date", "时间不能是当前时间之前");
            throw new MadaoException(ErrorEnum.PARAM_ERROR, map);
        }
        List<OrderMaster> orderMasterList = getOrderMaserByIdList(orderIdList);


        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(OrderMaster orderMaster: orderMasterList){
            if(!orderMaster.getShopId().equals(shopId)){
                if(!map.containsKey(ErrorEnum.ORDER_SHOP_OWNER_ERROR))
                    map.put(ErrorEnum.ORDER_SHOP_OWNER_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_SHOP_OWNER_ERROR).add(orderMaster.getOrderId());
                log.error("【异常】={}", ErrorEnum.ORDER_SHOP_OWNER_ERROR.getMessage());
            }
            if(!orderMaster.getDeliveryStatus().equals(DeliveryStatusEnum.WAIT.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_DELIVERY))
                    map.put(ErrorEnum.ORDER_HAD_DELIVERY, new ArrayList<>());
                map.get(ErrorEnum.ORDER_HAD_DELIVERY).add(orderMaster.getOrderId());
            }
            if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderMaster.getOrderId());
            }
            orderMaster.setDeliveryTime(deliveryDate);
            orderMaster.setArriveTime(arriveDate);
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.SET_DELIVERY_TIME_FAIL, getResultViewList(map));
        return orderIdList;
    }


    //商家设置拼购订单配送时间和送达时间
    public List<String> updateDeliveryTimePg(Long shopId, List<String> orderIdList, Long date1, Long date2){
        Date deliveryDate = (date1==null ? null: new Date(date1));
        Date arriveDate = (date2==null ? null : new Date(date2));
        if((deliveryDate!=null && deliveryDate.before(new Date())) || (arriveDate!=null && arriveDate.before(new Date()))){
            Map<String, String> map = new HashMap<>();
            map.put("date", "时间不能是当前时间之前");
            throw new MadaoException(ErrorEnum.PARAM_ERROR, map);
        }

        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(orderIdList);

        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            if(!pgOrderMaster.getDeliveryStatus().equals(DeliveryStatusEnum.WAIT.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_HAD_DELIVERY))
                    map.put(ErrorEnum.ORDER_HAD_DELIVERY, new ArrayList<>());
                map.get(ErrorEnum.ORDER_HAD_DELIVERY).add(pgOrderMaster.getOrderId());
            }
            if(!(pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode())|| pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode()))){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            pgOrderMaster.setDeliveryTime(deliveryDate);
            pgOrderMaster.setArriveTime(arriveDate);
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.SET_DELIVERY_TIME_FAIL, getResultViewList(map));
        return orderIdList;
    }

    //设置订单评价         供调用
    public void setComment(Long userId, String orderId, Long commentId){
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        log.info(orderMaster.toString());
        if(!orderMaster.getUserId().equals(userId)){
            log.error("【异常】={}" + ErrorEnum.ORDER_OWNER_ERROR.getMessage());
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, orderMaster.getOrderId());
        }
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.FINISH.getCode())){
            log.error("【异常】={}" + ErrorEnum.ORDER_NOT_FINISH.getMessage());
            throw new MadaoException(ErrorEnum.ORDER_NOT_FINISH, orderMaster.getOrderId());
        }
        if(!orderMaster.getCommentStatus().equals(CommentStatusEnum.WAIT.getCode())){
            log.error("【异常】={}" + ErrorEnum.ORDER_HAD_COMMENT.getMessage());
            throw new MadaoException(ErrorEnum.ORDER_HAD_COMMENT, orderMaster.getOrderId());
        }
        orderMaster.setCommentId(commentId);
        orderMaster.setCommentStatus(CommentStatusEnum.FINISH.getCode());
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
    }

    //用户确认收货普通订单
    public void confirm(Long userId, List<String> orderIdList){
        List<OrderMaster> orderMasterList = getOrderMaserByIdList(orderIdList);
        //异常检测

        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(OrderMaster orderMaster: orderMasterList ){
            if(!orderMaster.getUserId().equals(userId)){
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage() );
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(orderMaster.getOrderId());
            }
            if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage() + orderMaster.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderMaster.getOrderId());
            }
            orderMaster.setDeliveryStatus(DeliveryStatusEnum.FINSH.getCode());
            orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
            orderMaster.setReceiveTime(new Date());
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.CONFIRM_RECEIVE_FAIL, getResultViewList(map));
    }

    //用户确认收货 拼购订单
    public void confirmPg(Long userId, List<String> orderIdList){
        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(orderIdList);
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            if(!pgOrderMaster.getUserId().equals(userId)){
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage() );
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(pgOrderMaster.getOrderId());
            }

            if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage() + pgOrderMaster.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            pgOrderMaster.setDeliveryStatus(DeliveryStatusEnum.FINSH.getCode());
            pgOrderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
            pgOrderMaster.setReceiveTime(new Date());
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.CONFIRM_RECEIVE_FAIL, getResultViewList(map));
        }
    }

    //商家确认取消普通订单
    public List<OrderDTO> confirmCancel(Long shopId, List<String> orderIdList){
        List<OrderDTO> orderDTOList = getOrderDTObyIdList(orderIdList);
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        OrderMaster orderMaster = new OrderMaster();
        for(OrderDTO orderDTO: orderDTOList){
            if(!orderDTO.getOrderId().equals(shopId)){
                if(!map.containsKey(ErrorEnum.ORDER_SHOP_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_SHOP_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_SHOP_OWNER_ERROR).add(orderDTO.getOrderId());
            }
            if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.TO_CANCEL)){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(orderDTO.getOrderId());
            }
            orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.CONFIRM_CANCEL_FAIL, getResultViewList(map));

        return orderDTOList;
    }

    //商家确认取消拼购订单
    public List<PgOrderDTO> confirmCancelPg(Long shopId, List<String> orderIdList){
        //Todo 对商家的检验
        List<PgOrderDTO> pgOrderDTOList = getPgOrderDTObyIdList(orderIdList);
        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderDTO pgOrderDTO: pgOrderDTOList){
            if(!pgOrderDTO.getOrderStatus().equals(PgOrderEnum.TO_CANCEL.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage() + pgOrderDTO.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderDTO.getOrderId());
            }
            pgOrderDTO.setOrderStatus(PgOrderEnum.CANCEL.getCode());
            BeanUtils.copyProperties(pgOrderDTO, pgOrderMaster);
            pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.CONFIRM_CANCEL_FAIL, getResultViewList(map));
        return pgOrderDTOList;
    }


    //用户设置普通订单留言
    public void setOrderRemark(Long userId, String orderId, String remark){
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if(orderMaster==null)
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, orderId);
        if(!orderMaster.getUserId().equals(userId))
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR, IdResultMap.getIdMap(orderId));
        orderMaster.setOrderRemark(remark);
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
    }

    //用户设置拼购订单留言
    public void setPgOrderRemark(Long userId, String orderId, String remark){
        PgOrderMaster pgOrderMaster = pgOrderMasterMapper.selectByPrimaryKey(orderId);
        if(pgOrderMaster==null)
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, orderId);
        if(!pgOrderMaster.getUserId().equals(userId))
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode()))
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR, IdResultMap.getIdMap(orderId));
        pgOrderMaster.setOrderRemark(remark);
        pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
    }



    public List<OrderMaster> getOrderMaserByIdList(List<String> orderIdList){
        OrderMasterExample example = new OrderMasterExample();
        OrderMasterExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIdList);
        List<OrderMaster> orderMasterList = orderMasterMapper.selectByExample(example);
        if(orderMasterList.size()!=orderIdList.size()){
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
            for(OrderMaster orderMaster: orderMasterList)
                id.remove(orderMaster.getOrderId());
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(id));
        }
        return orderMasterList;
    }

    public List<PgOrderMaster> getPgOrderMasterByIdList(List<String> orderIdList){
        PgOrderMasterExample example = new PgOrderMasterExample();
        PgOrderMasterExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIdList);
        List<PgOrderMaster> pgOrderMasterList = pgOrderMasterMapper.selectByExample(example);
        if(pgOrderMasterList.size()!=orderIdList.size()){
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
            for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
                id.remove(pgOrderMaster.getOrderId());
            }
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, id);
        }
        return pgOrderMasterList;
    }

    public List<OrderDTO> getOrderDTObyIdList(List<String> orderIdList){
        List<OrderDTO> orderDTOList = commonMapper.getOrderByOrderIdList(orderIdList);
        if(orderDTOList.size()!=orderIdList.size()){
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
           for(OrderDTO orderDTO: orderDTOList)
               id.remove(orderDTO.getOrderId());
           throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(id));
        }
        return orderDTOList;
    }

    public List<PgOrderDTO> getPgOrderDTObyIdList(List<String> orderIdList){
        List<PgOrderDTO> pgOrderDTOList = commonMapper.getPgOrderByOrderIdList(orderIdList);
        if(pgOrderDTOList.size()!=orderIdList.size()){
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
            for(PgOrderDTO pgorderDTO: pgOrderDTOList)
                id.remove(pgorderDTO.getOrderId());
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(id));
        }
        return pgOrderDTOList;
    }








//    public List<PgOrderDTO> getPgOrderByUserId(Long userId, Byte status) {
//        List<PgOrderDTO> pgOrderDTOList = new ArrayList<>();
//        PgOrderMasterExample example = new PgOrderMasterExample();
//        PgOrderMasterExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(userId);
//        if(status!=null){
//            criteria.andOrderStatusEqualTo(status);
//        }
//        List<PgOrderMaster> pgOrderMasterList = pgOrderMasterMapper.selectByExample(example);
//        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
//
////            List<OrderItem> orderItemList = orderItemMapper.selectByExample();
//        }
//        return pgOrderDTOList;
//    }
}

//    public void addOrder(OrderForm orderForm) {
//        Long addressId = orderForm.getAddressId();
//        Long userId = orderForm.getUserId();
//        List<String> cartItemIdList = orderForm.getCartItemIdList();
//        UserAddress address = commonMapper.getUserAddressById(orderForm.getAddressId());
//        if (address.getUserId() != orderForm.getUserId()) {
//            log.error("用户地址信息错误");
//            throw new ResultException("用户地址信息错误");
//        }
//
//        //获取该订单的所有购物车项
//        List<CartItemDTO> cartItemDTOList = commonMapper.getCartItemDTOByCartItem(cartItemIdList);
//        //分类存放商品id
//        List<Long> pgProductIdList = new ArrayList<>();
//        List<Long> productIdList = new ArrayList<>();
//        //得到商铺-购物项键值对和拼购商品列表
//        Map<Long, List<CartItemDTO>> shopIdCartItemDTOMap = new HashMap<>();
//        List<CartItemDTO> pgCartItemDTOList = new ArrayList<>();
//
//        for (CartItemDTO cartItemDTO : cartItemDTOList) {
//            //如果是拼购购物车项，加入该列表
//            if (cartItemDTO.getItemCategory() == 1) {
//                pgCartItemDTOList.add(cartItemDTO);
//                pgProductIdList.add(cartItemDTO.getProductId());
//            } else {
//                //否则，根据商铺id加入map
//                Long shopId = cartItemDTO.getShopId();
//                if (!shopIdCartItemDTOMap.containsKey(shopId)) {
//                    shopIdCartItemDTOMap.put(shopId, new ArrayList<>());
//                }
//                productIdList.add(cartItemDTO.getProductId());
//                shopIdCartItemDTOMap.get(shopId).add(cartItemDTO);
//            }
//        }
//
//
//        //处理拼购订单
//        if (pgCartItemDTOList.size() > 0) {
//            String orderId = KeyUtil.genUniquKey();
//            BigDecimal orderAmount = BigDecimal.ZERO;
//
//            for (CartItemDTO cartItemDTO : pgCartItemDTOList) {
//                    orderAmount = orderAmount.add(cartItemDTO.getDiscountPrice().multiply(BigDecimal.valueOf(cartItemDTO.getProductQuantity())));
//                    OrderItem orderItem = new OrderItem();
//                    BeanUtils.copyProperties(cartItemDTO, orderItem);
//                    orderItem.setItemId(KeyUtil.genUniquKey());
//                    orderItem.setProductPrice(cartItemDTO.getDiscountPrice());
//                    orderItem.setOrderId(orderId);
//                    orderItemMapper.insertSelective(orderItem);
//                }
//
//                PgOrderMaster pgOrderMaster = new PgOrderMaster();
//                pgOrderMaster.setUserId(orderForm.getUserId());
//                pgOrderMaster.setUserName(orderForm.getUserName());
//                pgOrderMaster.setUserPhone(address.getPhone());
//                pgOrderMaster.setUserAddress(address.getUserAddress());
//                pgOrderMaster.setOrderAmount(orderAmount);
//                pgOrderMaster.setOrderId(orderId);
//
//                pgOrderMasterMapper.insertSelective(pgOrderMaster);
//
//                decreaseStock(pgCartItemDTOList);
//            }
//
//        //处理普通商品订单
//        if(shopIdCartItemDTOMap.size()>0){
//            for(Map.Entry entry: shopIdCartItemDTOMap.entrySet()){
//                List<CartItemDTO> list = (List<CartItemDTO>) entry.getValue();
//                String orderId = KeyUtil.genUniquKey();
//                BigDecimal orderAmount = BigDecimal.ZERO;
//                Long shopId = list.get(0).getShopId();
//                String shopName = list.get(0).getShopName();
//
//                decreaseStock(list);
//                for(CartItemDTO cartItemDTO: list){
//                    orderAmount = orderAmount.add(cartItemDTO.getProductPrice().multiply(BigDecimal.valueOf(cartItemDTO.getProductQuantity())));
//                    OrderItem orderItem = new OrderItem();
//                    BeanUtils.copyProperties(cartItemDTO, orderItem);
//                    orderItem.setItemId(KeyUtil.genUniquKey());
//                    orderItem.setProductPrice(cartItemDTO.getProductPrice());
//                    orderItemMapper.insertSelective(orderItem);
//                }
//
//                OrderMaster orderMaster = new OrderMaster();
//
//                orderMaster.setShopId(shopId);
//                orderMaster.setShopName(shopName);
//                orderMaster.setUserId(orderForm.getUserId());
//                orderMaster.setUserName(orderForm.getUserName());
//                orderMaster.setUserPhone(address.getPhone());
//                orderMaster.setUserAddress(address.getUserAddress());
//                orderMaster.setOrderAmount(orderAmount);
//                orderMaster.setOrderId(orderId);
//                orderMasterMapper.insertSelective(orderMaster);
//
//            }
//        }
//
//
//        }







