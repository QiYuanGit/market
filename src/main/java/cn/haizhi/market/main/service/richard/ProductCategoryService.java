package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.ProductCategory;
import cn.haizhi.market.main.bean.richard.ProductCategoryExample;
import cn.haizhi.market.main.mapper.richard.ProductCategoryMapper;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Service
@Transactional
public class ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public void insert(ProductCategory form){
        form.setCategoryId(BeanUtil.getId());
        if(BeanUtil.isEmpty(form.getCategoryName())){
            throw new ResultException("分类名称不能为空！");
        }
        productCategoryMapper.insertSelective(form);
    }

    public void update(ProductCategory form){
        ProductCategory record = this.getone(form.getCategoryId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        if(BeanUtil.isEmpty(form.getCategoryName())){
            throw new ResultException("分类名称不能为空！");
        }
        BeanUtil.copyBean(form,record);
        productCategoryMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(Long id){
        if(BeanUtil.isNull(this.getone(id))){
            throw new ResultException("记录不存在！");
        }
        productCategoryMapper.deleteByPrimaryKey(id);
    }

    public ProductCategory getone(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    public List<ProductCategory> getall(ProductCategory form)throws Exception{
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notEmpty(form.getCategoryName())){
            criteria.andCategoryNameLike(BeanUtil.isLike(form.getCategoryName()));
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return productCategoryMapper.selectByExample(example);
    }

}
