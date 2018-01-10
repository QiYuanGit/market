package cn.haizhi.market.main.mapper.richard;

import cn.haizhi.market.main.bean.richard.ProductCategory;
import cn.haizhi.market.main.bean.richard.ProductCategoryExample;
import java.util.List;

public interface ProductCategoryMapper {
    long countByExample(ProductCategoryExample example);

    int deleteByPrimaryKey(Long categoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    List<ProductCategory> selectByExample(ProductCategoryExample example);

    ProductCategory selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
}