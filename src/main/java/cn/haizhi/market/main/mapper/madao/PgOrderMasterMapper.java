package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.PgOrderMaster;
import cn.haizhi.market.main.bean.madao.PgOrderMasterExample;
import java.util.List;

public interface PgOrderMasterMapper {
    long countByExample(PgOrderMasterExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(PgOrderMaster record);

    int insertSelective(PgOrderMaster record);

    List<PgOrderMaster> selectByExample(PgOrderMasterExample example);

    PgOrderMaster selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(PgOrderMaster record);

    int updateByPrimaryKey(PgOrderMaster record);
}