package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.Product;
import cn.haizhi.market.main.bean.richard.ProductExample;
import cn.haizhi.market.main.mapper.richard.ProductMapper;
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
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> selectLot(Product form)throws Exception{
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notEmpty(form.getProductName())){
            criteria.andProductNameLike(BeanUtil.isLike(form.getProductName()));
        }
        if(BeanUtil.notNull(form.getShopId())){
            criteria.andShopIdEqualTo(form.getShopId());
        }
        if(BeanUtil.notNull(form.getCategoryId())){
            criteria.andCategoryIdEqualTo(form.getCategoryId());
        }
        if(BeanUtil.notEmpty(form.getIdList())) {
            criteria.andCategoryIdIn(form.getIdList());
        }

        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return productMapper.selectByExample(example);
    }

    public Product selectOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return productMapper.selectByPrimaryKey(id);
    }

    public void insertOne(Product form){
        form.setProductId(BeanUtil.getId());
        productMapper.insertSelective(form);
    }

    public void updateOne(Product form){
        Product record = this.selectOne(form.getProductId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        BeanUtil.copyBean(form,record);
        productMapper.updateByPrimaryKeySelective(record);
    }

    public void deleteOne(Long id){
        if(BeanUtil.isNull(this.selectOne(id))){
            throw new ResultException("记录不存在！");
        }
        productMapper.deleteByPrimaryKey(id);
    }

}
