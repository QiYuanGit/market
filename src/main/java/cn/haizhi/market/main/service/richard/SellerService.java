package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.Seller;
import cn.haizhi.market.main.bean.richard.SellerExample;
import cn.haizhi.market.main.mapper.richard.SellerMapper;
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
public class SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    public void insert(Seller form){
        form.setSellerId(BeanUtil.getId());
        sellerMapper.insertSelective(form);
    }

    public void update(Seller form){
        Seller record = this.getone(form.getSellerId());
        if(BeanUtil.isNull(record)){
            throw new ResultException("记录不存在！");
        }
        BeanUtil.copyBean(form,record);
        sellerMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(Long id){
        if(BeanUtil.isNull(this.getone(id))){
            throw new ResultException("记录不存在！");
        }
        sellerMapper.deleteByPrimaryKey(id);
    }

    public Seller getone(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return sellerMapper.selectByPrimaryKey(id);
    }

    public List<Seller> getall(Seller form)throws Exception{
        SellerExample example = new SellerExample();
        SellerExample.Criteria criteria = example.createCriteria();

        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return sellerMapper.selectByExample(example);
    }

}
