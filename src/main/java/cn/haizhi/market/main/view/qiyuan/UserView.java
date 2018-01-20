package cn.haizhi.market.main.view.qiyuan;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserView {
    private Long userId;

    private String userName;

    private String userHeadPath;

    private String userPhone;

    private List<UserAddressView> userAddresses;
}
