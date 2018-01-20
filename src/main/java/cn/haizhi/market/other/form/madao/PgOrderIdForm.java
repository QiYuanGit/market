package cn.haizhi.market.other.form.madao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PgOrderIdForm {
    @NotNull(message = "商家id不能为空")
    Long shopId;
    @NotEmpty(message = "订单id不能为空")
    List<String> orderIdList;
}
