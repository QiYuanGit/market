package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.ShopPcategory;
import cn.haizhi.market.main.bean.richard.ShopPcategoryExample;
import cn.haizhi.market.main.mapper.richard.ShopPcategoryMapper;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date: 2018/1/10
 * Author: Richard
 */

@Service
@Transactional
public class ShopPcategoryService {

    @Autowired
    private ShopPcategoryMapper shopPcategoryMapper;

    public void insert(ShopPcategory form){
        form.setJoinId(BeanUtil.getId());
        shopPcategoryMapper.insertSelective(form);
    }

    public void update(ShopPcategory form){
        ShopPcategory record = this.getone(form.getJoinId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        BeanUtil.copyBean(form,record);
        shopPcategoryMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(Long id){
        if(BeanUtil.isNull(this.getone(id))){
            throw new ResultException("记录不存在！");
        }
        shopPcategoryMapper.deleteByPrimaryKey(id);
    }

    public ShopPcategory getone(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return shopPcategoryMapper.selectByPrimaryKey(id);
    }

    public List<ShopPcategory> getall(ShopPcategory form)throws Exception{
        ShopPcategoryExample example = new ShopPcategoryExample();
        ShopPcategoryExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notNull(form.getShopId())){
            criteria.andShopIdEqualTo(form.getShopId());
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return shopPcategoryMapper.selectByExample(example);
    }
}
