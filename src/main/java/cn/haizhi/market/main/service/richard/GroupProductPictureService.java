package cn.haizhi.market.main.service.richard;

import cn.haizhi.market.main.bean.richard.GroupProductPicture;
import cn.haizhi.market.main.bean.richard.GroupProductPictureExample;
import cn.haizhi.market.main.mapper.richard.GroupProductPictureMapper;
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
public class GroupProductPictureService {

    @Autowired
    private GroupProductPictureMapper groupProductPictureMapper;

    public List<GroupProductPicture> selectLot(GroupProductPicture form)throws Exception{
        GroupProductPictureExample example = new GroupProductPictureExample();
        GroupProductPictureExample.Criteria criteria = example.createCriteria();
        if(BeanUtil.notEmpty(form.getIdList())){
            criteria.andProductIdIn(form.getIdList());
        }
        if(BeanUtil.notNull(form.getProductId())){
            criteria.andProductIdEqualTo(form.getProductId());
        }
        if(BeanUtil.notNull(form.getPageNum()) && BeanUtil.notNull(form.getPageSize())){
            PageHelper.startPage(form.getPageNum(),form.getPageSize());
        }
        return groupProductPictureMapper.selectByExample(example);
    }

    public GroupProductPicture selectOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空！");
        }
        return groupProductPictureMapper.selectByPrimaryKey(id);
    }

    public void insertOne(GroupProductPicture form){
        form.setPictureId(BeanUtil.getId());
        groupProductPictureMapper.insertSelective(form);
    }

    public void updateOne(GroupProductPicture form){
        if(BeanUtil.isNull(this.selectOne(form.getPictureId()))){
            throw new ResultException("记录不存在！");
        }
        groupProductPictureMapper.updateByPrimaryKeySelective(form);
    }

    public void deleteOne(Long id){
        if(BeanUtil.isNull(this.selectOne(id))){
            throw new ResultException("记录不存在！");
        }
        groupProductPictureMapper.deleteByPrimaryKey(id);
    }

}
