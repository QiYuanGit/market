package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.PgOrderMaster;
import cn.haizhi.market.main.bean.madao.PgOrderMasterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PgOrderMasterMapper {
    long countByExample(PgOrderMasterExample example);

    int deleteByExample(PgOrderMasterExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(PgOrderMaster record);

    int insertSelective(PgOrderMaster record);

    List<PgOrderMaster> selectByExample(PgOrderMasterExample example);

    PgOrderMaster selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") PgOrderMaster record, @Param("example") PgOrderMasterExample example);

    int updateByExample(@Param("record") PgOrderMaster record, @Param("example") PgOrderMasterExample example);

    int updateByPrimaryKeySelective(PgOrderMaster record);

    int updateByPrimaryKey(PgOrderMaster record);
}