package cn.haizhi.market.main.view;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Date: 2018/1/11
 * Author: Richard
 */

@Data
public class PageView {

    Integer pageNum;
    Integer pageSize;
    Integer totalPages;
    Boolean isFirstPage;
    Boolean isLastPage;
    List<?> list;

    public PageView(List<?> list) {
        PageInfo pageInfo = new PageInfo<>(list);
       this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalPages = pageInfo.getPages();
        this.isFirstPage = pageInfo.isIsFirstPage();
        this.isLastPage = pageInfo.isIsLastPage();
        this.list = list;
    }
}
