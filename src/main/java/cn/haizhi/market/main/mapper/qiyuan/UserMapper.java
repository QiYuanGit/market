package cn.haizhi.market.main.mapper.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.User;
import cn.haizhi.market.main.bean.qiyuan.UserExample;
import cn.haizhi.market.main.view.qiyuan.UserView;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserView getUserWithAddress(Long id);
}