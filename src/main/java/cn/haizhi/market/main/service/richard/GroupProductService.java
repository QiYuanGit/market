package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.GroupProduct;
import cn.haizhi.market.main.bean.richard.GroupProductExample;
import cn.haizhi.market.main.mapper.richard.GroupProductMapper;
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
public class GroupProductService {

    @Autowired
    private GroupProductMapper groupProductMapper;

    public List<GroupProduct> selectLot(GroupProduct form)throws Exception{
        GroupProductExample example = new GroupProductExample();
        GroupProductExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notEmpty(form.getProductName())){
            criteria.andProductNameLike(BeanUtil.isLike(form.getProductName()));
        }
        if(BeanUtil.notNull(form.getCategoryId())){
            criteria.andCategoryIdEqualTo(form.getCategoryId());
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return groupProductMapper.selectByExample(example);
    }

    public GroupProduct selectOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return groupProductMapper.selectByPrimaryKey(id);
    }

    public void insertOne(GroupProduct form){
        form.setProductId(BeanUtil.getId());
        groupProductMapper.insertSelective(form);
    }

    public void updateOne(GroupProduct form){
        if(BeanUtil.isNull(this.selectOne(form.getProductId()))){
            throw new ResultException("记录不存在！");
        }
        groupProductMapper.updateByPrimaryKeySelective(form);
    }

    public void deleteOne(Long id){
        if(BeanUtil.isNull(this.selectOne(id))){
            throw new ResultException("记录不存在！");
        }
        groupProductMapper.deleteByPrimaryKey(id);
    }

}
