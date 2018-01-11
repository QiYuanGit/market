package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.Seller;
import cn.haizhi.market.main.bean.richard.SellerExample;
import java.util.List;

public interface SellerMapper {
    long countByExample(SellerExample example);

    int deleteByPrimaryKey(Long sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    List<Seller> selectByExample(SellerExample example);

    Seller selectByPrimaryKey(Long sellerId);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}