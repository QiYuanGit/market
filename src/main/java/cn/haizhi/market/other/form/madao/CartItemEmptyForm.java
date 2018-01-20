package cn.haizhi.market.other.form.madao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemEmptyForm {
    @NotNull(message = "用户id为空")
    Long userId;
    @NotNull(message = "购物车类型为空")
    Byte category;
}
