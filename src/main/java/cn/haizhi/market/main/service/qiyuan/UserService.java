package cn.haizhi.market.main.service.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.User;
import cn.haizhi.market.main.bean.qiyuan.UserExample;
import cn.haizhi.market.main.mapper.qiyuan.UserMapper;
import cn.haizhi.market.main.view.qiyuan.UserView;
import cn.haizhi.market.other.enums.qiyuan.UserEnum;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.exception.qiyuan.QiException;
import cn.haizhi.market.other.form.UserForm;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.qiyuan.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id找到用户
     * @param id
     * @return
     */
    public User getOne(Long id){
        if(BeanUtil.isNull(id)) {
            throw new ResultException("用户id不能为空");
        }
        return userMapper.selectByPrimaryKey(id);
    }
    /****************************************************************************************/
    public void SendMessage(UserForm userForm,HttpSession session){
        //1、检验手机号码是否为空
        if (zewei.isNull(userForm.getUserPhone())){
            throw new QiException(UserEnum.EMPTYPHONE_RESULT.getCode(),UserEnum.EMPTYPHONE_RESULT.getHint());
        }
        //2、判断手机号码的格式是否正确
        if (!UserValidate.isHandset(userForm.getUserPhone())){
            throw new QiException(UserEnum.ERRORPHONE_RESULT.getCode(),UserEnum.ERRORPHONE_RESULT.getHint());
        }
        final String charset = "utf-8";
        String account = "N7911438";
        String pswd = "ChuangLan168";
        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        //产生随机验证码
        String smsCode = (""+Math.random()*1000000).substring(0,6);
        //把验证码保存到session里面
        session.setAttribute(Const.verification,smsCode);
        //设置session的有效时间为300秒
        session.setMaxInactiveInterval(5*60);
        // 短信内容
        //String msg = "【便易菜市】尊敬的用户，您好，您正在注册便易菜市APP，验证码为：" +"56564"+ "，若非本人操作请忽略此短信。";
        String msg = "【253云通讯】尊敬的用户，您好，您正在注册便易菜市APP,验证码为："+smsCode+"，若非本人操作请忽略此短信。";
        //手机号码
        String phone = userForm.getUserPhone();
        //状态报告
        String report= "true";
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        System.out.println("before request string is: " + requestJson);
        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
        System.out.println("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        System.out.println("response  toString is :" + smsSingleResponse);
    }


    //短信登录 点击登录按钮之后的操作
    /*
    1、没注册-生成用户id，用户名
    2、有注册获取验证码后直接登录
     */
    public User login(UserForm userForm, HttpSession session, HttpServletResponse response){
        //首先判断是短信登录还是密码登录
        if(userForm.getChose() == 0){//手机号登录
            //有一个函数先去发送验证码
            //发的验证码只需要保存验证码到session就好了，采用json的格式发送
            //1、判断输入的验证码是否为空
            //测试
            //session.setAttribute(userForm.getUserPhone()+userForm.getSmsCode(),"123456");
            if(zewei.isNull(userForm.getSmsCode())){
                throw new QiException(UserEnum.LOSTMESSAGE_RESULT.getCode(),UserEnum.LOSTMESSAGE_RESULT.getHint());
            }
            String getCode = (String)session.getAttribute(Const.verification);
            //2、判断输入的验证码和session里面的验证码是否相等
            if (!getCode.equals(userForm.getSmsCode())){
                throw new QiException(UserEnum.MESSAGE_RESULT.getCode(),UserEnum.MESSAGE_RESULT.getHint());
            }
            //3、判断手机号有没有被注册
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserPhoneEqualTo(userForm.getUserPhone());
            List<User> listUser = userMapper.selectByExample(userExample);
            if(BeanUtil.isEmpty(listUser)){
                //没有注册
                //1、生成一个User并存进数据库
                User user = new User();
                Long id = BeanUtil.getId();
                user.setUserId(id);
                //获取随机6位数字字符串
                String sixNum = (""+Math.random()*1000000).substring(0,6);
                user.setUserName(RandDomString.getSix()+sixNum);
                user.setUserPhone(userForm.getUserPhone());
                //保存注册的时间
                user.setRegister(new Date());
                //2、将user存进数据库
                userMapper.insert(user);
                //3、去掉密码
                //4、用session保持登录状态
                session.setAttribute(Const.CURRENT_USER,user);
                //5、设置session的失效时间
                session.setMaxInactiveInterval(Const.SSO_SESSION_EXPIRE);
                //6、将sessionId存进cookie
                Cookie cookie = new Cookie("JSESSIONID",session.getId());
                //7、设置cookie的过期时间
                cookie.setMaxAge(Const.SSO_SESSION_EXPIRE);
                //8、将cookie存进response
                response.addCookie(cookie);
                //9、返回user给安卓端
                return user;
            }else{//有注册
                //1、获取当前的用户
                User user = listUser.get(0);
                //2、设置密码为空，保证在session和返回安卓端的密码为空
                user.setUserPassword(null);
                //3、将当前的user写进session
                session.setAttribute(Const.CURRENT_USER,user);
                //4、设置session的过期时间
                session.setMaxInactiveInterval(Const.SSO_SESSION_EXPIRE);
                //5、将session存进cookie
                Cookie cookie = new Cookie("JSESSIONID",session.getId());
                //6、设置cookie的过期时间
                cookie.setMaxAge(Const.SSO_SESSION_EXPIRE);
                //7、将cookie存进response
                response.addCookie(cookie);
                //8、返回user给安卓端
                return user;
            }
        }else{//用户名、手机号密码登录
            //首要前提，判断是用户名登录还是手机号登录
                //看能不能通过手机号验证，能通过就是手机号登录
                //否则就是用户名登录
            //1、判断用户名是否为空
            if (zewei.isNull(userForm.getUserName())){
                throw new QiException(UserEnum.LOSTNAME_RESULT.getCode(),UserEnum.LOSTNAME_RESULT.getHint());
            }
            //2、判断密码是否为空
            if(zewei.isNull(userForm.getUserPassword())){
                throw new QiException(UserEnum.LOSTPASSWORD_RESULT.getCode(),UserEnum.LOSTPASSWORD_RESULT.getHint());
            }
            /*3、小插曲-判断是用户名登录还是手机号登录**/
            List<User> userList = null;
            if (UserValidate.isHandset(userForm.getUserName())){
                //判断为真就是手机号登录
                //判断手机号有没有注册
                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria();
                criteria.andUserPhoneEqualTo(userForm.getUserName());
                userList = userMapper.selectByExample(userExample);
                if(BeanUtil.isEmpty(userList)){
                    //手机号未注册，提示前去注册
                    throw new QiException(UserEnum.ENPHONE_RESULT.getCode(),UserEnum.ENPHONE_RESULT.getHint());
                }
                //已经注册,下面和用户名统一判断密码是否正确
            }else{
                //根据用户名查询数据库判断这个用户是否存在
                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria();
                criteria.andUserNameEqualTo(userForm.getUserName());
                userList = userMapper.selectByExample(userExample);
                if (BeanUtil.isEmpty(userList)){
                    //根据用户名查询，该用户不存在
                    throw new QiException(UserEnum.NOUSER_RESULT.getCode(),UserEnum.NOUSER_RESULT.getHint());
                }
                //用户名存在，下面和手机号统一判断密码是否正确
            }
            //4、如果存在则判断这个用户的密码正不正确
            //获取当前用户的密码
            User user = userList.get(0);
            if (zewei.isNull(userForm.getUserPassword())){
                //密码还没设置
                //提示该用户的密码错误
                throw new QiException(UserEnum.ERRORPASSWORD_RESULT.getCode(),UserEnum.ERRORPASSWORD_RESULT.getHint());
            }else{
                //密码已经设置
                //核对密码
                //获取表单里面的密码
                String formPassword = userForm.getUserPassword();
                //表单里面的密码进行MD5加密
                if(!MD5.getMD5ofStr(userForm.getUserPassword()).equals(user.getUserPassword())){
                    throw new QiException(UserEnum.ERRORPASSWORD_RESULT.getCode(),UserEnum.ERRORPASSWORD_RESULT.getHint());
                }else{
                    //5、去除密码，保证安全
                    user.setUserPassword(null);
                    //6、将该用户写进sesion
                    session.setAttribute(Const.CURRENT_USER,user);
                    //7、设置session的过期时间
                    session.setMaxInactiveInterval(Const.SSO_SESSION_EXPIRE);
                    //8、将session存进cookie
                    Cookie cookie = new Cookie("JSESSIONID",session.getId());
                    //9、设置cookie的过期时间
                    cookie.setMaxAge(Const.SSO_SESSION_EXPIRE);
                    //10、将cookie添加进response
                    response.addCookie(cookie);
                    //11、返回user给安卓端
                    return user;
                }
            }
        }
    }
    //用户退出功能
    public void loginout(HttpSession session) {
        //1、获取session里面的当前的用户对象
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        /*额外添加，需要获取session的id，从里面找到user*/
        user = this.getOne(user.getUserId());
        //2、检查密码是否设置
        if (zewei.isNull(user.getUserPassword())) {
            //没有设置密码
            throw new QiException(UserEnum.SETPASSWORD_RESULT.getCode(), UserEnum.SETPASSWORD_RESULT.getHint());
            //此时还没退出
       } else {
           session.invalidate();
       }
    }
    //用户不设置密码立即退出
    public void loginoutImmediately(HttpSession session) {
            session.invalidate();
    }
    //用户添加密码功能-退出的时候添加，需要安装端再次发起请求
    public void addPassword(UserForm form,HttpSession session){
        //1、取出session里面的用户
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //2、设置用户的密码
        user.setUserPassword(MD5.getMD5ofStr(form.getUserPassword()));
        //3、更新用户
        userMapper.updateByPrimaryKeySelective(user);
        //4、退出登录,设置session无效
        session.invalidate();
    }
    //刷新session信息
    public User updateSession(HttpSession session){
        //1、获取session,判断session是否存在
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (BeanUtil.isNull(user)){
            throw new QiException(UserEnum.NEEDLOGIN_RESULT.getCode(),UserEnum.NEEDLOGIN_RESULT.getHint());
        }
        //2、重新设置session的过期时间
        session.setMaxInactiveInterval(Const.SSO_SESSION_EXPIRE);
        //3、返回user对象给安卓端
        return user;
    }
    //上传用户头像
    public void uploadHeadPic(User form,HttpSession session, CommonsMultipartFile file, HttpServletRequest request) throws Exception {
        //1、获取图片的原始名称
        String originnalFilename = file.getOriginalFilename();
        //2、上传图片
        if(file != null && originnalFilename !=null && originnalFilename.length()>0){
            //存储图片的路径
            String path = request.getServletContext().getRealPath(File.separator);
            //新的图片名称
            String newFileName = UUID.randomUUID()+originnalFilename.substring(originnalFilename.lastIndexOf("."));
            File newFile = new File(path+ File.separatorChar+ "HeadPic"+ File.separatorChar+ newFileName);
            if(!newFile.exists()){
                newFile.mkdir();
            }
            //新图片存进服务器中
            file.transferTo(newFile);
            //获取session中的对象
            User user = this.getOne(form.getUserId());
            //判断user对象中是否有图片
            if(!BeanUtil.isNull(user.getUserHeadPath())){
                //执行删除动作
                String deletePath = path+"\\HeadPic\\"+user.getUserHeadPath();
                File deleteFile = new File(deletePath);
                if (deleteFile.exists()){
                    deleteFile.delete();
                    user.setUserHeadPath(null);
                }
            }
            user.setUserHeadPath(newFileName);
            //更新数据库的user对象
            userMapper.updateByPrimaryKeySelective(user);
            //更新session中的用户信息
            user.setUserPassword(null);
            session.setAttribute(Const.CURRENT_USER,user);
        }else{
            //提示图片上传失败
            throw new QiException(UserEnum.ERRORPIC_RESULT.getCode(),UserEnum.ERRORPIC_RESULT.getHint());
        }
    }
    //修改用户密码-一律发短信
    public void updatePwd(UserForm userForm,HttpSession session){
        //已经发送短信验证码
        //1、从session中获得用户对象
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //2、检验短信验证码是否正确
        String code = (String) session.getAttribute(Const.verification);
        //判断验证码是否为空
        if(zewei.isNull(code)){
            throw new QiException(UserEnum.LOSTMESSAGE_RESULT.getCode(),UserEnum.LOSTMESSAGE_RESULT.getHint());
        }
        if(!userForm.getSmsCode().equals(code)){
            throw new QiException(UserEnum.MESSAGE_RESULT.getCode(),UserEnum.MESSAGE_RESULT.getHint());
        }
        //3、判断密码是否为空
        if (zewei.isNull(userForm.getUserPassword())){
            throw new QiException(UserEnum.LOSTPASSWORD_RESULT.getCode(),UserEnum.LOSTPASSWORD_RESULT.getHint());
        }
        //4、修改用户对象的密码
        user.setUserPassword(MD5.getMD5ofStr(userForm.getUserPassword()));
        //5、保存进数据库
        userMapper.updateByPrimaryKeySelective(user);
        //注销session，保证安全
        session.invalidate();
    }
    //修改用户名
    public User updateName(UserForm userForm,HttpSession session){
        //1、获取session中的用户
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //2、获取数据库的用户，因为含有密码
        user = this.getOne(user.getUserId());
        //3、判断用户名是否为空
        if(zewei.isNull(userForm.getUserName())){
            throw new QiException(UserEnum.LOSTNAME_RESULT.getCode(),UserEnum.LOSTNAME_RESULT.getHint());
        }
        //4、判断用户名设置是都符合规范
        if(!UserValidate.checkUserNamePassword(userForm.getUserName())){
            throw new QiException(UserEnum.ERRORNAME_RESULT.getCode(),UserEnum.ERRORNAME_RESULT.getHint());
        }
        //小插曲-判断这个用户名有没有人注册
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userForm.getUserName());
        List<User> userList = userMapper.selectByExample(userExample);
        if(BeanUtil.isNull(userList) || userList.size() == 0){
            //5、更改用户名
            user.setUserName(userForm.getUserName());
            //6、存进数据库
            userMapper.updateByPrimaryKeySelective(user);
            //7、更新session中的user信息
            user.setUserPassword(null);
            session.setAttribute(Const.CURRENT_USER,user);
            return user;
        }else{
            throw new QiException(UserEnum.HADUSERNAME_RESULT.getCode(),UserEnum.HADUSERNAME_RESULT.getHint());
        }

    }

    //根据当前登录用户id查询用户信息和收货地址所有信息
    public UserView getOneUserWith(Long id){
        UserView userView = userMapper.getUserWithAddress(id);
        return userView;
    }
    //找到所有用户
    public List<User> getAll(User form){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return userMapper.selectByExample(example);
    }
    //根据用户id返回用户信息
    public User findUserById(Long id){
        if(BeanUtil.isNull(id)) {
            throw new ResultException("用户id不能为空");
        }
        return userMapper.selectByPrimaryKey(id);
    }

}
