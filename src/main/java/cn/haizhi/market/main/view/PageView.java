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

    public PageView(List<?> list){
        PageInfo<?> page = new PageInfo<>(list);
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalPages = page.getPages();
        this.isFirstPage = page.isIsFirstPage();
        this.isLastPage = page.isIsLastPage();
        this.list = list;
    }

}
