package cn.haizhi.market.other.form.madao;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PgCartItemForm {
    @NotNull(message = "用户id为空")
    private Long userId;
    @NotNull(message = "商品id为空")
    private Long productId;
    @NotNull(message = "商品数量为空")
    @Min(value=1, message = "商品数量不能少于1")
    private Integer productQuantity;

    public PgCartItemForm(Long userId, Long productId, Integer productQuantity) {
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public PgCartItemForm() {
    }
}
