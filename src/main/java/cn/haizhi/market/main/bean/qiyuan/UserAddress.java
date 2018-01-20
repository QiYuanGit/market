package cn.haizhi.market.main.bean.qiyuan;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAddress extends BaseBean {
    private Long addressId;

    private String userAddress;

    private String phone;

    private Long userId;

    private String houseNumber;

    private Boolean isDefault;

    private String consignee;


}