package cn.haizhi.market.main.handler.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.User;
import cn.haizhi.market.main.service.qiyuan.UserService;
import cn.haizhi.market.main.view.ResultView;
import cn.haizhi.market.main.view.qiyuan.UserView;
import cn.haizhi.market.other.form.UserForm;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserHandler {
    @Autowired
    private UserService userService;

    /**********************************************************************/
    //1、发送短信
    @PostMapping(value = "/user/sendMessage",produces = "application/json;charset=UTF-8")
    public ResultView sendMessage(@RequestBody  UserForm userForm,HttpSession session){
        userService.SendMessage(userForm,session);
        return ResultUtil.returnSuccess();
    }
    //2、登录和注册
    @PostMapping(value = "/user/login",produces = "application/json;charset=UTF-8")
    public ResultView sendMessage(@RequestBody UserForm userForm,HttpSession session,HttpServletResponse response){
        User user = userService.login(userForm, session, response);
        return ResultUtil.returnSuccess(user);
    }
    //3、退出登录
    @GetMapping(value = "/user/loginout",produces = "application/json;charset=UTF-8")
    public ResultView loginout(HttpSession session){
        userService.loginout(session);
        return ResultUtil.returnSuccess();
    }
    //4、退出登录的时候添加密码
    @PostMapping(value = "/user/addPassword",produces = "application/json;charset=UTF-8")
    public ResultView addPassword(@RequestBody UserForm userForm,HttpSession session){
        userService.addPassword(userForm,session);
        return ResultUtil.returnSuccess();
    }
    //5、刷新session信息
    @GetMapping(value = "/user/updateSession",produces = "application/json;charset=UTF-8")
    public ResultView updateSession(HttpSession session){
        User user = userService.updateSession(session);
        return ResultUtil.returnSuccess(user);
    }
    //6、下一步开始掌握session的刷新时间和拦截器的操作
    //7、上传用户头像
    @PostMapping ("/user/uploadPic")
    public ResultView uploadHeadPic(User form, HttpSession session, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws Exception {
        userService.uploadHeadPic(form,session,file,request);
        return ResultUtil.returnSuccess();
    }
    //8、登录状态下修改用户密码
    @PostMapping("/user/updatePwd")
    public ResultView updatePwd(@RequestBody UserForm userForm,HttpSession session){
        userService.updatePwd(userForm,session);
        return ResultUtil.returnSuccess();
    }
    //9、修改用户名
    @RequestMapping("/user/updateName")
    public ResultView updateName(@RequestBody UserForm userForm,HttpSession session){
        User user = userService.updateName(userForm, session);
        return ResultUtil.returnSuccess(user);
    }
    //10、根据当前登录用户id查询用户信息和收货地址所有信息
   @GetMapping(value = "/user/oneUserAndAddress/{id}",produces = "application/json;charset=UTF-8")
    public ResultView getOneUserWithAddress(@PathVariable("id") Long id){
        UserView userWithAddress = userService.getOneUserWith(id);
       return ResultUtil.returnSuccess(userWithAddress);
    }
    //11、找到所有的用户
    @GetMapping(value = "/user/getAll",produces = "application/json;charset=UTF-8")
    public ResultView getAll(User form) throws Exception{
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            return ResultUtil.returnSuccess(new PageInfo<>(userService.getAll(form)));
        }else{
            return ResultUtil.returnSuccess(userService.getAll(form));
        }
    }
    //12、用户立即退出，不管有没有设置密码
    @GetMapping(value = "/user/loginoutImmediately",produces = "application/json;charset=UTF-8")
    public ResultView log(HttpSession session){
        userService.loginoutImmediately(session);
        return ResultUtil.returnSuccess();
    }
    //13、根据用户id返回当前用户信息

    @GetMapping(value = "/user/get/{id}",produces = "application/json;charset=UTF-8")
    public ResultView getOneUser(@PathVariable("id") Long id){
        return ResultUtil.returnSuccess(userService.findUserById(id));
    }
}
