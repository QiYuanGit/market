package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.PgCartItem;
import cn.haizhi.market.main.bean.madao.PgCartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PgCartItemMapper {
    long countByExample(PgCartItemExample example);

    int deleteByExample(PgCartItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(PgCartItem record);

    int insertSelective(PgCartItem record);

    List<PgCartItem> selectByExample(PgCartItemExample example);

    PgCartItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") PgCartItem record, @Param("example") PgCartItemExample example);

    int updateByExample(@Param("record") PgCartItem record, @Param("example") PgCartItemExample example);

    int updateByPrimaryKeySelective(PgCartItem record);

    int updateByPrimaryKey(PgCartItem record);
}