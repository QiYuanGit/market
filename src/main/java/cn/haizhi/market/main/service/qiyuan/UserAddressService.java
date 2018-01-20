package cn.haizhi.market.main.service.qiyuan;

import cn.haizhi.market.main.bean.qiyuan.UserAddress;
import cn.haizhi.market.main.bean.qiyuan.UserAddressExample;
import cn.haizhi.market.main.mapper.qiyuan.UserAddressMapper;
import cn.haizhi.market.other.enums.qiyuan.UserEnum;
import cn.haizhi.market.other.exception.ResultException;
import cn.haizhi.market.other.exception.qiyuan.QiException;
import cn.haizhi.market.other.form.UserAddressForm;
import cn.haizhi.market.other.util.BeanUtil;
import cn.haizhi.market.other.util.qiyuan.UserValidate;
import cn.haizhi.market.other.util.qiyuan.zewei;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    //1、添加
    public UserAddress insert(UserAddress form){
        form.setAddressId(BeanUtil.getId());
        //1、检验地址是否为空
        if(zewei.isNull(form.getUserAddress())){
            throw new QiException(UserEnum.EMPTYADDRESS_RESULT.getCode(),UserEnum.EMPTYADDRESS_RESULT.getHint());
        }
        //2、检验收货电话是否为空
        if(zewei.isNull(form.getPhone())){
            throw new QiException(UserEnum.EMPTYAPHONE_RESULT.getCode(),UserEnum.EMPTYAPHONE_RESULT.getHint());
        }
        //3、检验收货电话是否合法
        if(!UserValidate.isHandset(form.getPhone())){
            throw new QiException(UserEnum.ERRORPHONE_RESULT.getCode(),UserEnum.ERRORPHONE_RESULT.getHint());
        }
        //4、收货人不能为空
        if(zewei.isNull(form.getConsignee())){
            throw new QiException(UserEnum.EMPTYPERSON_RESULT.getCode(),UserEnum.EMPTYPERSON_RESULT.getHint());
        }
        form.setIsDefault(false);
        userAddressMapper.insert(form);
        return form;
    }
    //更新收货地址
    public UserAddress update(UserAddressForm form){
        //进行完整性判断
        UserAddress userAddress = this.getOne(form.getAddressId());
        if(BeanUtil.isNull(userAddress)){
            throw new ResultException("记录不存在!");
        }
        //收货地址不能为空
        if(zewei.isNull(form.getUserAddress())){
            throw new QiException(UserEnum.EMPTYADDRESS_RESULT.getCode(),UserEnum.EMPTYADDRESS_RESULT.getHint());
        }
        //2、检验收货电话是否为空
        if(zewei.isNull(form.getPhone())){
            throw new QiException(UserEnum.EMPTYAPHONE_RESULT.getCode(),UserEnum.EMPTYAPHONE_RESULT.getHint());
        }
        //3、检验收货电话是否合法
        if(!UserValidate.isHandset(form.getPhone())){
            throw new QiException(UserEnum.ERRORPHONE_RESULT.getCode(),UserEnum.ERRORPHONE_RESULT.getHint());
        }
        //4、收货人不能为空
        if(zewei.isNull(form.getConsignee())){
            throw new QiException(UserEnum.EMPTYPERSON_RESULT.getCode(),UserEnum.EMPTYPERSON_RESULT.getHint());
        }
        BeanUtil.copyBean(form,userAddress);
        userAddressMapper.updateByPrimaryKey(userAddress);
        return userAddress;
    }
    //根据地址id获取收货地址
    public UserAddress getOne(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空!");
        }
        UserAddress userAddress  = userAddressMapper.selectByPrimaryKey(id);
        return userAddress;
    }
    //删除收货地址
    public void delete(Long id){
        if(BeanUtil.isNull(this.getOne(id))){
            throw new ResultException("记录不存在!");
        }
        userAddressMapper.deleteByPrimaryKey(id);
    }
    //获取全部收货地址
    public List<UserAddress> getAll(UserAddress userAddress) throws Exception{
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        return userAddressMapper.selectByExample(userAddressExample);
    }
    //根据用户id获取所有的收货地址
    public List<UserAddress> getOneUserAllAddress(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空!");
        }
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(userAddressExample);
        return userAddressList;
    }
    //根据用户id修改默认收货地址
    public void updateDefaultAddress(UserAddress form){
        //1、先找出该用户所有的收货地址
        List<UserAddress> oneUserAllAddress = this.getOneUserAllAddress(form.getUserId());
        //2、判断所有的收货地址中有没有默认收货地址，有，修改并更新
        for(UserAddress userAddress:oneUserAllAddress){
            if(userAddress.getIsDefault().equals(true)){
                userAddress.setIsDefault(false);
                userAddressMapper.updateByPrimaryKeySelective(userAddress);
            }
        }
        //3、找到该用户的收货地址
        UserAddress record =  this.getOne(form.getAddressId());
        //4、修改它为默认收货地址
        record.setIsDefault(true);
        //5、保存进数据库
        userAddressMapper.updateByPrimaryKeySelective(record);
    }
    //根据用户id获得默认收货地址
    public UserAddress getDefaultAddress(Long id){
        if(BeanUtil.isNull(id)){
            throw new ResultException("编号不能为空!");
        }
        //根据用户id获取所有收货地址
        List<UserAddress> userAddresses = this.getOneUserAllAddress(id);
        //2、判断所有的收货地址中有没有默认收货地址，有，返回
        for(UserAddress userAddress:userAddresses){
            if(userAddress.getIsDefault().equals(true)){
                return userAddress;
            }
        }
        return null;
    }
}
