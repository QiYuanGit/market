package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.PgGroup;
import cn.haizhi.market.main.bean.madao.PgGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PgGroupMapper {
    long countByExample(PgGroupExample example);

    int deleteByExample(PgGroupExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(PgGroup record);

    int insertSelective(PgGroup record);

    List<PgGroup> selectByExample(PgGroupExample example);

    PgGroup selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") PgGroup record, @Param("example") PgGroupExample example);

    int updateByExample(@Param("record") PgGroup record, @Param("example") PgGroupExample example);

    int updateByPrimaryKeySelective(PgGroup record);

    int updateByPrimaryKey(PgGroup record);
}