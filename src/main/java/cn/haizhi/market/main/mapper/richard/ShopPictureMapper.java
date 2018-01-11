package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.ShopPicture;
import cn.haizhi.market.main.bean.richard.ShopPictureExample;
import java.util.List;

public interface ShopPictureMapper {
    long countByExample(ShopPictureExample example);

    int deleteByPrimaryKey(Long pictureId);

    int insert(ShopPicture record);

    int insertSelective(ShopPicture record);

    List<ShopPicture> selectByExample(ShopPictureExample example);

    ShopPicture selectByPrimaryKey(Long pictureId);

    int updateByPrimaryKeySelective(ShopPicture record);

    int updateByPrimaryKey(ShopPicture record);
}