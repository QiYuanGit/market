package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.ShopComment;
import cn.haizhi.market.main.bean.richard.ShopCommentExample;
import cn.haizhi.market.main.view.richard.ShopCommentView;

import java.util.List;

public interface ShopCommentMapper {

    List<ShopCommentView> selectWithJoin(ShopComment form);

    long countByExample(ShopCommentExample example);

    int deleteByPrimaryKey(Long commentId);

    int insert(ShopComment record);

    int insertSelective(ShopComment record);

    List<ShopComment> selectByExample(ShopCommentExample example);

    ShopComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(ShopComment record);

    int updateByPrimaryKey(ShopComment record);
}