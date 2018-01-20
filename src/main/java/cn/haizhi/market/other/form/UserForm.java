package cn.haizhi.market.other.form;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserForm {
    private String userPhone;
    private String smsCode;
    private Integer chose;
    private String userName;
    private String userPassword;
}
