package cn.haizhi.market.main.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Date: 2018/1/9
 * Author: Richard
 */

@Data
public class BaseBean{

    protected Integer pageNum;
    protected Integer pageSize;
    protected Integer orderBy;
    protected List<Long> idList;
}
