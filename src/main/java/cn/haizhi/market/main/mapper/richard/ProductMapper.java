package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.bean.richard.ProductExample;
import java.util.List;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}