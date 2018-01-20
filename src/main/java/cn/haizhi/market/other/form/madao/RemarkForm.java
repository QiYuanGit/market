package cn.haizhi.market.other.form.madao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RemarkForm {
    @NotNull(message = "用户id为空")
    private Long userId;
    @NotNull(message="订单id为空")
    private String orderId;
    @NotNull(message = "留言为空")
    private String remark;
}
