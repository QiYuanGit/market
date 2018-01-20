package cn.haizhi.market.other.form.madao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDateForm {
    @NotNull(message = "商家id为空")
    private Long shopId;
    @NotEmpty(message = "订单为空")
    private List<String> orderIdList;
    @NotNull(message = "日期信息为空")
    private Long date;
}



