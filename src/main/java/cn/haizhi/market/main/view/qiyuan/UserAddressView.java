package cn.haizhi.market.main.view.qiyuan;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAddressView {
    private Long addressId;

    private String userAddress;

    private String phone;

    private String houseNumber;

    private Boolean isDefault;

    private Long userId;

    private String consignee;

    private UserView userView;
}
