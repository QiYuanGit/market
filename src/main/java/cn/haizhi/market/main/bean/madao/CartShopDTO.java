package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartShopDTO {
    private String cartId;
    private Long userId;
    private Long shopId;
    private String shopName;
    private Integer shopState;
    private BigDecimal limitPrice;
    private BigDecimal sendPrice;
    private List<CartItemDTO> cartItemDTOList;




}
