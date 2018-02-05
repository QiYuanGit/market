package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.*;
import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import org.apache.ibatis.type.JdbcType;

import java.sql.JDBCType;
import java.util.List;

public interface CommonMapper {
    ProductShop getShopInfoByProductId(Long productId);

    List<CartShopDTO> getCartShopDTOByUserId(Long userId);


    List<CartItemDTO> getCartItemDTOByUserId(Long userId);


    List<CartItemDTO> getCartItemDTOByCartItemIdList(List<String> cartItemIdList);

    UserAddress getUserAddressById(Long addressId);

    ShopPriceInfo getShopPriceInfo(Long shopId);

    List<OrderDTO> getOrderDTOByUserId(Long shopId, Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus);




    List<OrderDTO> getOrderByOrderIdList(List<String> orderIdList);

    List<PgOrderDTO> getPgOrderByUserId(Long userId, Byte orderStatus, Byte payStatus, Byte deliveryStatus, Byte commentStatus);

    List<PgOrderDTO> getPgOrderByOrderIdList(List<String> orderIdList);

    void increaseStock(Long productId, int num);

    PgOrderDTO getPgOrderByOrderId(String orderId);

    OrderDTO getOrderByOrderId(String orderId);

    GroupDTO getGroupDTOByGroupId(String groupId);


    List<GroupDTO> getGroupDTOByExample(Byte groupStatus, Byte activeStatus);

    List<PgCartItemDTO> getPgCartItemDTOListByUserId(Long userId);

    List<PgCartItemDTO> getPgCartItemDTOByCartItemIdList(List<String> pgCartItemId);

    void increaseGroupProductStock(Long productId, Integer productQuantity);
}