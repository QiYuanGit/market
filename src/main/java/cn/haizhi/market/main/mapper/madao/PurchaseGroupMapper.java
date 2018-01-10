package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.PurchaseGroup;
import cn.haizhi.market.main.bean.madao.PurchaseGroupExample;
import java.util.List;

public interface PurchaseGroupMapper {
    long countByExample(PurchaseGroupExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(PurchaseGroup record);

    int insertSelective(PurchaseGroup record);

    List<PurchaseGroup> selectByExample(PurchaseGroupExample example);

    PurchaseGroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(PurchaseGroup record);

    int updateByPrimaryKey(PurchaseGroup record);
}