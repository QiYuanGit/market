package cn.haizhi.market.main.bean.qiyuan;

import cn.haizhi.market.main.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User extends BaseBean{
    private Long userId;

    private String userName;

    private String userPassword;

    private String userHeadPath;

    private Date register;

    private String userPhone;

}