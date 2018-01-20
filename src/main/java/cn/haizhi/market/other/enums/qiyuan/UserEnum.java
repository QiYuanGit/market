package cn.haizhi.market.other.enums.qiyuan;

import lombok.Getter;

@Getter
public enum UserEnum {
    FAILURE_RESULT(0,"失败！"),
    SUCCESS_RESULT(1,"成功！"),
    ERROR_RESULT(2,"错误！"),
    LOSTNAME_RESULT(100,"用户名为空！"),
    LOSTPASSWORD_RESULT(101,"密码为空！"),
    NOUSER_RESULT(102,"用户不存在！"),
    ERRORPASSWORD_RESULT(103,"密码不正确！"),
    MESSAGE_RESULT(104,"输入的验证码不正确！"),
    LOSTMESSAGE_RESULT(105,"输入的验证码为空！"),
    EMPTYPHONE_RESULT(106,"输入的手机号码为空！"),
    ERRORPHONE_RESULT(107,"输入的手机号码不合法！"),
    ENPHONE_RESULT(108,"该手机号未注册，请前去注册！"),
    SETPASSWORD_RESULT(109,"密码还没设置，请设置密码，方便下次登录！"),
    NEEDLOGIN_RESULT(110,"登录已过期，请重新登录！"),
    TOLOGIN_RESULT(111,"该操作需要用户登录！"),
    ERRORPIC_RESULT(112,"图片上传失败！"),
    ERRORNAME_RESULT(113,"用户名不符合规范！"),
    FORMATPWD_RESULT(114,"密码不符合规范！"),
    EMPTYADDRESS_RESULT(115,"请输入收货地址！"),
    EMPTYAPHONE_RESULT(117,"请输入收货电话！"),
    NONEADDRESS_RESULT(118,"该用户还没有收货地址！"),
    HADUSERNAME_RESULT(119,"该用户名已被注册！"),
    EMPTYPERSON_RESULT(120,"收货人不能为空！");
    UserEnum(Integer code, String hint){
        this.code = code;
        this.hint = hint;
    }
    private Integer code;
    private String hint;
}
