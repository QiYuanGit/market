package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.ShopPicture;
import cn.haizhi.market.main.bean.richard.ShopPictureExample;
import cn.haizhi.market.main.mapper.richard.ShopPictureMapper;
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
public class ShopPictureService {

    @Autowired
    private ShopPictureMapper shopPictureMapper;

    public List<ShopPicture> selectLog(ShopPicture form)throws Exception{
        ShopPictureExample example = new ShopPictureExample();
        ShopPictureExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notNull(form.getShopId())){
            criteria.andShopIdEqualTo(form.getShopId());
        }
        if(BeanUtil.notEmpty(form.getIdList())){
            criteria.andShopIdIn(form.getIdList());
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return shopPictureMapper.selectByExample(example);
    }

    public ShopPicture selectOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return shopPictureMapper.selectByPrimaryKey(id);
    }

    public void insertOne(ShopPicture form){
        form.setPictureId(BeanUtil.getId());
        shopPictureMapper.insertSelective(form);
    }

    public void updateOne(ShopPicture form){
        if(BeanUtil.isNull(this.selectOne(form.getPictureId()))){
            throw new ResultException("记录不存在！");
        }
        shopPictureMapper.updateByPrimaryKeySelective(form);
    }

    public void delete(Long id){
        if(BeanUtil.isNull(this.selectOne(id))){
            throw new ResultException("记录不存在！");
        }
        shopPictureMapper.deleteByPrimaryKey(id);
    }
}
