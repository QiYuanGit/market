package cn.haizhi.market.main.mapper.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.bean.qiyuan.UserAddressExample;
import java.util.List;

public interface UserAddressMapper {
    long countByExample(UserAddressExample example);

    int deleteByPrimaryKey(Long addressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}