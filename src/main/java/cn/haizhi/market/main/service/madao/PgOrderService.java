package cn.haizhi.market.main.service.madao;

import cn.haizhi.market.main.bean.madao.*;
import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.bean.richard.GroupProduct;
import cn.haizhi.market.main.mapper.madao.CommonMapper;
import cn.haizhi.market.main.mapper.madao.OrderItemMapper;
import cn.haizhi.market.main.mapper.madao.PgOrderMasterMapper;
import cn.haizhi.market.main.mapper.richard.GroupProductMapper;
import cn.haizhi.market.other.enums.madao.*;
import cn.haizhi.market.other.exception.MadaoException;
import cn.haizhi.market.other.form.madao.OrderCreateForm;
import cn.haizhi.market.other.form.madao.OrderDeliveryStatusForm;
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

@Service
@Slf4j
public class PgOrderService {
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private PgOrderMasterMapper pgOrderMasterMapper;
    @Autowired
    private GroupProductMapper groupProductMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    //添加拼购订单
    public PgOrderDTO addPgOrder(OrderCreateForm orderForm) {
        //用于返回生成的订单
        PgOrderDTO pgOrderDTO = new PgOrderDTO();
        pgOrderDTO.setOrderItemList(new ArrayList<>());
        UserAddress address = commonMapper.getUserAddressById(orderForm.getAddressId());
        //检查地址是否正确
        if (address==null || address.getUserId() != orderForm.getUserId()) {
            log.error("用户地址信息错误");
            throw new MadaoException(ErrorEnum.ADDRESS_ERROR, IdResultMap.getIdMap(orderForm.getAddressId()));
        }
        //获取该订单的所有购物车项
        List<PgCartItemDTO> pgCartItemDTOList = commonMapper.getPgCartItemDTOByCartItemIdList(orderForm.getCartItemIdList());

        //如果查出的购物项数量和传入表单的数量不一致，获取查不出的项的id抛出异常
        if(!(pgCartItemDTOList.size()==orderForm.getCartItemIdList().size())){
            List<String> id = new LinkedList<>();
            id.addAll(orderForm.getCartItemIdList());
            for(PgCartItemDTO pgCartItemDTO: pgCartItemDTOList){
                id.remove(pgCartItemDTO.getItemId());
            }
            throw new MadaoException(ErrorEnum.CARTITEM_ERROR, IdResultMap.getIdMap(id));
        }

        //处理拼购订单
        String orderId = KeyUtil.genUniquKey();
        BigDecimal orderAmount = BigDecimal.ZERO;

        List<String> id = null;
        for (PgCartItemDTO pgCartItemDTO : pgCartItemDTOList) {
            if(!pgCartItemDTO.getUserId().equals(orderForm.getUserId())){
                if(id==null)
                    id = new ArrayList<>();
                id.add(pgCartItemDTO.getItemId());
            }
            orderAmount = orderAmount.add(pgCartItemDTO.getProductNprice().multiply(BigDecimal.valueOf(pgCartItemDTO.getProductQuantity())));
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(pgCartItemDTO, orderItem);
            orderItem.setItemId(KeyUtil.genUniquKey());
            orderItem.setProductPrice(pgCartItemDTO.getProductNprice());
            orderItem.setOrderId(orderId);
            int result = orderItemMapper.insertSelective(orderItem);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
            pgOrderDTO.getOrderItemList().add(orderItem);
        }
        if(id!=null)
            throw new MadaoException(ErrorEnum.CARTITEM_OWNER_ERROR, IdResultMap.getIdMap(id));

        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        pgOrderMaster.setUserId(orderForm.getUserId());
        pgOrderMaster.setUserName(orderForm.getUserName());
        pgOrderMaster.setUserPhone(address.getPhone());
        pgOrderMaster.setUserAddress(address.getUserAddress());
        pgOrderMaster.setOrderAmount(orderAmount);
        pgOrderMaster.setOrderId(orderId);

        int result = pgOrderMasterMapper.insertSelective(pgOrderMaster);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        BeanUtils.copyProperties(pgOrderMaster, pgOrderDTO);
        decreaseStock(pgCartItemDTOList);
        return pgOrderDTO;
    }

    //根据购物车项列表扣库存
    public void decreaseStock(List<PgCartItemDTO> pgCartItemDTOList){
        //存放扣库存时错误的商品的  错误信息---购物项 键值对
        Map<ErrorEnum, List<String>> map = new HashMap<>();

        for(PgCartItemDTO pgCartItemDTO: pgCartItemDTOList){
            GroupProduct groupProduct = groupProductMapper.selectByPrimaryKey(pgCartItemDTO.getProductId());
            if(groupProduct==null) {
                if (!map.containsKey(ErrorEnum.PRODUCT_NOT_EXIST)) {
                    map.put(ErrorEnum.PRODUCT_NOT_EXIST, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_NOT_EXIST).add(pgCartItemDTO.getItemId());
            }
//            TODO 商品下架的属性
//            if (groupProduct.getProductS) {
//                if(!map.containsKey(ErrorEnum.PRODUCT_DOWN)){
//                    map.put(ErrorEnum.PRODUCT_DOWN, new ArrayList<>());
//                }
//                map.get(ErrorEnum.PRODUCT_DOWN).add(pgCartItemDTO.getItemId());
//            }

            Integer result = groupProduct.getProductStock() - pgCartItemDTO.getProductQuantity();
            if(result<0) {
                log.error("【商品库存不足】-------Need={}---------product={}", pgCartItemDTO.getProductQuantity(), groupProduct);
                if(!map.containsKey(ErrorEnum.PRODUCT_STOCK_ERROR)){
                    map.put(ErrorEnum.PRODUCT_STOCK_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.PRODUCT_STOCK_ERROR).add(pgCartItemDTO.getItemId());
            }

            groupProduct.setProductStock(result);
            int result1 = groupProductMapper.updateByPrimaryKeySelective(groupProduct);
            if(result1<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_CREATE_ERROR, getResultViewList(map));
        }
    }


    public List<PgOrderDTO> getPgOrderDTO(Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus){
        return commonMapper.getPgOrderByUserId(userId, orderStatus, payStatus, deliveryStatus, commentStatus);
    }

    //用户支付拼购订单
    public void payPgOrder(Long userId, List<String> orderIdList) {
        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(orderIdList);
        BigDecimal amount = BigDecimal.ZERO;
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            if(!pgOrderMaster.getUserId().equals(userId)){
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR)){
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(pgOrderMaster.getOrderId());
            }
            if(!pgOrderMaster.getOrderStatus().equals( PgOrderEnum.NEW.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR)){
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            if(!pgOrderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_PAY_STATUS_ERROR)){
                    map.put(ErrorEnum.ORDER_PAY_STATUS_ERROR, new ArrayList<>());
                }
                map.get(ErrorEnum.ORDER_PAY_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            amount = amount.add(pgOrderMaster.getOrderAmount());
        }

        if (map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_PAY_FAIL, getResultViewList(map));
        }

        //Todo 支付

        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            pgOrderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
    }

    //退单后加库存操作
    public void increaseStock(List<OrderItem> orderItemList){
        for(OrderItem orderItem: orderItemList){
            commonMapper.increaseGroupProductStock(orderItem.getProductId(), orderItem.getProductQuantity());
        }
    }

    //用户发起取消拼购订单
    public List<PgOrderMaster> cancelPgOrderByUser(Long userId, List<String> pgOrderIdList){
        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(pgOrderIdList);

        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            if(!userId.equals(pgOrderMaster.getUserId())){
                log.error("【异常】={}", ErrorEnum.ORDER_OWNER_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_OWNER_ERROR))
                    map.put(ErrorEnum.ORDER_OWNER_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_OWNER_ERROR).add(pgOrderMaster.getOrderId());
            }
            if(pgOrderMaster.getOrderStatus().equals(PgOrderEnum.TO_CANCEL.getCode()) || pgOrderMaster.getOrderStatus().equals(PgOrderEnum.CANCEL.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }

            //判断时间信息是否可用,不可用则加入异常
            if(pgOrderMaster.getDeliveryStatus().equals(DeliveryStatusEnum.FINSH.getCode())){
                Date date = null;
                if(pgOrderMaster.getArriveTime()!=null)
                    date = pgOrderMaster.getArriveTime();
                else if(pgOrderMaster.getDeliveryTime()!=null)
                    date = pgOrderMaster.getDeliveryTime();
                else if(pgOrderMaster.getReceiveTime()!=null)
                    date = pgOrderMaster.getReceiveTime();
                if(date==null){
                    if(!map.containsKey(ErrorEnum.ORDER_DATE_ERROR)){
                        map.put(ErrorEnum.ORDER_DATE_ERROR, new ArrayList<>());
                    }
                    map.get(ErrorEnum.ORDER_DATE_ERROR).add(pgOrderMaster.getOrderId());
                }else if((new Date().getTime() - date.getTime())>86400000L){
                    if(!map.containsKey(ErrorEnum.ORDER_HAD_OVERDUE))
                        map.put(ErrorEnum.ORDER_HAD_OVERDUE, new ArrayList<>());
                    map.get(ErrorEnum.ORDER_HAD_OVERDUE).add(pgOrderMaster.getOrderId());
                }
            }
            pgOrderMaster.setOrderStatus(PgOrderEnum.TO_CANCEL.getCode());
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.ORDER_CANCEL_FAIL, getResultViewList(map));

        return pgOrderMasterList;
    }


    //商家确认取消拼购订单
    public List<PgOrderDTO> confirmCancelPg(Long shopId, List<String> orderIdList){
        //Todo 对商家的检验
        List<PgOrderDTO> pgOrderDTOList = getPgOrderDTObyIdList(orderIdList);
        PgOrderMaster pgOrderMaster = new PgOrderMaster();
        List<OrderItem> orderItemList = new ArrayList<>();
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
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
            orderItemList.addAll(pgOrderDTO.getOrderItemList());
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.CONFIRM_CANCEL_FAIL, getResultViewList(map));

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
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.ORDER_CANCEL_FAIL, getResultViewList(map));
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
    public List<String> updateDeliveryTime(Long shopId, List<String> orderIdList, Long date1) {
        //判断时间是否合法
        Date deliveryDate = new Date(date1);
        if (deliveryDate.before(new Date())) {
            Map<String, String> map = new HashMap<>();
            map.put("date", "时间不能是当前时间之前");
            throw new MadaoException(ErrorEnum.PARAM_ERROR, map);
        }

        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(orderIdList);
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for (PgOrderMaster pgOrderMaster : pgOrderMasterList) {
            if (pgOrderMaster.getDeliveryStatus().equals(DeliveryStatusEnum.FINSH.getCode())) {
                if (!map.containsKey(ErrorEnum.ORDER_HAD_DELIVERY))
                    map.put(ErrorEnum.ORDER_HAD_DELIVERY, new ArrayList<>());
                map.get(ErrorEnum.ORDER_HAD_DELIVERY).add(pgOrderMaster.getOrderId());
            }
            if (!(pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode())|| pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode()))) {
                if (!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            pgOrderMaster.setDeliveryTime(deliveryDate);
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if (map.size() > 0)
            throw new MadaoException(ErrorEnum.SET_DELIVERY_TIME_FAIL, getResultViewList(map));
        return orderIdList;
    }

    //用户确认收货 拼购订单  Todo是否应该完成订单
    @Transactional
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

            if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode())){
                log.error("【异常】={}", ErrorEnum.ORDER_STATUS_ERROR.getMessage() + pgOrderMaster.getOrderId());
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            pgOrderMaster.setDeliveryStatus(DeliveryStatusEnum.FINSH.getCode());
            pgOrderMaster.setOrderStatus(PgOrderEnum.FINISH.getCode());
            pgOrderMaster.setReceiveTime(new Date());
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if(map.size()>0){
            throw new MadaoException(ErrorEnum.CONFIRM_RECEIVE_FAIL, getResultViewList(map));
        }
    }




    //用户设置拼购订单留言
    public void setPgOrderRemark(Long userId, String orderId, String remark) {
        PgOrderMaster pgOrderMaster = pgOrderMasterMapper.selectByPrimaryKey(orderId);
        if (pgOrderMaster == null)
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, orderId);
        if (!pgOrderMaster.getUserId().equals(userId))
            throw new MadaoException(ErrorEnum.ORDER_OWNER_ERROR, IdResultMap.getIdMap(orderId));
        if (!(pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode()) || pgOrderMaster.getOrderStatus().equals(PgOrderEnum.NEW.getCode())))
            throw new MadaoException(ErrorEnum.ORDER_STATUS_ERROR, IdResultMap.getIdMap(orderId));
        pgOrderMaster.setOrderRemark(remark);
        int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
        if(result<=0)
            throw new MadaoException(ErrorEnum.OPERATION_FAIL);
    }

    //商家确认送达
    public void confirmArriveByShopPg(Long shopId, List<String> orderIdList, Long arrive) {
        Date date = new Date(arrive);
        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(orderIdList);
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        for (PgOrderMaster pgOrderMaster : pgOrderMasterList) {
            pgOrderMaster.setArriveTime(date);
            pgOrderMaster.setDeliveryStatus(DeliveryStatusEnum.FINSH.getCode());
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
    }


    public List<PgOrderDTO> getPgOrderDTObyIdList(List<String> orderIdList) {
        List<PgOrderDTO> pgOrderDTOList = commonMapper.getPgOrderByOrderIdList(orderIdList);
        if (pgOrderDTOList.size() != orderIdList.size()) {
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
            for (PgOrderDTO pgorderDTO : pgOrderDTOList)
                id.remove(pgorderDTO.getOrderId());
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(id));
        }
        return pgOrderDTOList;
    }

    public List<PgOrderMaster> getPgOrderMasterByIdList(List<String> orderIdList) {
        PgOrderMasterExample example = new PgOrderMasterExample();
        PgOrderMasterExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIdList);
        List<PgOrderMaster> pgOrderMasterList = pgOrderMasterMapper.selectByExample(example);
        if (pgOrderMasterList.size() != orderIdList.size()) {
            List<String> id = new LinkedList<>();
            id.addAll(orderIdList);
            for (PgOrderMaster pgOrderMaster : pgOrderMasterList) {
                id.remove(pgOrderMaster.getOrderId());
            }
            throw new MadaoException(ErrorEnum.ORDER_NOT_EXIST, IdResultMap.getIdMap(id));
        }
        return pgOrderMasterList;
    }

    //商家设置订单为配送中
    public void setOrderDeliveryStatusSending(OrderDeliveryStatusForm form) {
        //Todo 验证拼购商家
        Map<ErrorEnum, List<String>> map = new HashMap<>();
        List<PgOrderMaster> pgOrderMasterList = getPgOrderMasterByIdList(form.getOrderId());
        for(PgOrderMaster pgOrderMaster: pgOrderMasterList){
            if(!pgOrderMaster.getOrderStatus().equals(PgOrderEnum.IN_GROUP.getCode())){
                if(!map.containsKey(ErrorEnum.ORDER_STATUS_ERROR))
                    map.put(ErrorEnum.ORDER_STATUS_ERROR, new ArrayList<>());
                map.get(ErrorEnum.ORDER_STATUS_ERROR).add(pgOrderMaster.getOrderId());
            }
            pgOrderMaster.setDeliveryStatus(DeliveryStatusEnum.DELIVERYING.getCode());
            int result = pgOrderMasterMapper.updateByPrimaryKeySelective(pgOrderMaster);
            if(result<=0)
                throw new MadaoException(ErrorEnum.OPERATION_FAIL);
        }
        if(map.size()>0)
            throw new MadaoException(ErrorEnum.ORDER_SET_DELIVERY_FAIL, getResultViewList(map));
    }
}