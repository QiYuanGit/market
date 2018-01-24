package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.GroupProductPicture;
import cn.haizhi.market.main.bean.richard.GroupProductPictureExample;

import java.util.List;

public interface GroupProductPictureMapper {
    long countByExample(GroupProductPictureExample example);

    int deleteByPrimaryKey(Long pictureId);

    int insert(GroupProductPicture record);

    int insertSelective(GroupProductPicture record);

    List<GroupProductPicture> selectByExample(GroupProductPictureExample example);

    GroupProductPicture selectByPrimaryKey(Long pictureId);

    int updateByPrimaryKeySelective(GroupProductPicture record);

    int updateByPrimaryKey(GroupProductPicture record);
}