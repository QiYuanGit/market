package cn.haizhi.test;


import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.service.qiyuan.UserAddressService;
import cn.haizhi.market.main.service.qiyuan.UserService;
import cn.haizhi.market.main.view.qiyuan.UserView;
import cn.haizhi.market.other.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
@Slf4j
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;
    @Test
    public void test() throws Exception{
        for(int i=0;i<4;i++){
            UserAddress userAddress = new UserAddress();
            userAddress.setAddressId(BeanUtil.getId());
            userAddress.setHouseNumber("海大"+i);
            userAddress.setPhone("1892719582"+i);
            userAddress.setUserId(1516174895266L);
            userAddress.setIsDefault(false);
            userAddress.setUserAddress("广东海洋大学");
            userAddressService.insert(userAddress);
        }
    }
    @Test
    public void test1() throws Exception{
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(1516259411329L);
        userAddress.setUserId(1516174895266L);
        userAddressService.updateDefaultAddress(userAddress);
        List<UserAddress> userAddressList = userAddressService.getOneUserAllAddress(1516174895266L);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+userAddressList);
    }
}
