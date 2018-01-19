package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.bean.richard.ShopExample;
import cn.haizhi.market.main.mapper.richard.ShopMapper;
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
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;

    public List<Shop> selectLot(Shop form)throws Exception{
        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        StringBuffer orderByClause = new StringBuffer("shop_id asc");
        if(BeanUtil.notEmpty(form.getShopName())){
            criteria.andShopNameLike(BeanUtil.isLike(form.getShopName()));
        }
        if(BeanUtil.notNull(form.getIsRecom())){
            criteria.andIsRecomEqualTo(form.getIsRecom());
            orderByClause.append(",recom_order asc");
        }
        if(BeanUtil.notNull(form.getOrderBy())){
            switch (form.getOrderBy()){
                case 0 : orderByClause.append(",shop_grade desc");break;
                case 1 : orderByClause.append(",shop_sale desc");break;
            }
        }
        example.setOrderByClause(orderByClause.toString());
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return shopMapper.selectByExample(example);
    }

    public Shop selectOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return shopMapper.selectByPrimaryKey(id);
    }

    public void insertOne(Shop form){
        form.setShopId(BeanUtil.getId());
        shopMapper.insertSelective(form);
    }

    public void updateOne(Shop form){
        Shop record = this.selectOne(form.getShopId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        BeanUtil.copyBean(form,record);
        shopMapper.updateByPrimaryKeySelective(record);
    }

    public void deleteOne(Long id){
        if(BeanUtil.isNull(this.selectOne(id))){
            throw new ResultException("记录不存在！");
        }
        shopMapper.deleteByPrimaryKey(id);
    }

}
