package cn.haizhi.market.other.form.qiyuan;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAddressForm {
    private Long addressId;

    private String userAddress;

    private String phone;

    private Long userId;

    private String houseNumber;

    private String consignee;
}
