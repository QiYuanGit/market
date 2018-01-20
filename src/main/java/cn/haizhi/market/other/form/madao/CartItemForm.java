package cn.haizhi.market.other.form.madao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemForm {
    @NotNull(message = "商品为空")
    private Long productId;
    @NotNull(message = "用户id为空")
    private Long userId;
    @NotNull(message = "商品数量为空")
    @Min(value=1, message = "商品数量不能少于1")
    private Integer productQuantity;
    @NotNull(message = "购物车类别为空")
    private Byte itemCategory;

    public CartItemForm(Long productId, Long userId, Integer productQuantity, Byte itemCategory) {
        this.productId = productId;
        this.userId = userId;
        this.productQuantity = productQuantity;
        this.itemCategory = itemCategory;
    }

    public CartItemForm() {
    }
}
