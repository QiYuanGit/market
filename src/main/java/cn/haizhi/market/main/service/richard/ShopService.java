package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.Shop;
import cn.haizhi.market.main.bean.richard.ShopExample;
import cn.haizhi.market.main.mapper.richard.ShopMapper;
import cn.haizhi.market.main.view.richard.ShopView;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
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

    public void insert(Shop form){
        form.setShopId(BeanUtil.getId());
        shopMapper.insertSelective(form);
    }

    public void update(Shop form){
        Shop record = this.getone(form.getShopId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        BeanUtil.copyBean(form,record);
        shopMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(Long id){
        if(BeanUtil.isNull(this.getone(id))){
            throw new ResultException("记录不存在！");
        }
        shopMapper.deleteByPrimaryKey(id);
    }

    public Shop getone(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return shopMapper.selectByPrimaryKey(id);
    }

    public List<Shop> getall(Shop form)throws Exception{
        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notEmpty(form.getShopName())){
            criteria.andShopNameLike(BeanUtil.isLike(form.getShopName()));
        }
        if(BeanUtil.notNull(form.getIsRecom())){
            criteria.andIsRecomEqualTo(form.getIsRecom());
            example.setOrderByClause("recom_order asc");
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return shopMapper.selectByExample(example);
    }

    public ShopView getoneWithJoin(Long id){
        return shopMapper.getoneWithJoin(id);
    }

    public List<ShopView> getallWithJoin(Shop form) throws UnsupportedEncodingException {
        if(BeanUtil.notEmpty(form.getShopName())){
            form.setShopName(BeanUtil.isLike(form.getShopName()));
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return shopMapper.getallWithJoin(form);
    }

}
