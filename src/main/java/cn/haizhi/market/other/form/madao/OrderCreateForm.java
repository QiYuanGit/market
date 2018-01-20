package cn.haizhi.market.other.form.madao;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderCreateForm {
    @NotNull(message = "用id不能为空")
    private Long userId;
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message="地址不能为空")
    private Long addressId;
    @NotEmpty(message = "未选中购物项")
    private List<String> cartItemIdList;
}
