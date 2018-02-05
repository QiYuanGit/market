package cn.haizhi.market.other.form.madao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderUpdateForm {
    @NotNull(message = "用户id为空")
    private Long id;
    @NotEmpty(message = "订单项为空")
    private List<String> orderIdList;
    @NotNull(message = "支付类型为空")
    private Byte payWay;
}
