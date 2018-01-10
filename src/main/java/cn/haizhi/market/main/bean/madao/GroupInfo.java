package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.util.Date;

@Data
public class GroupInfo {
    private Date groupDate;

    private Integer groupNum;

    public GroupInfo(Date groupDate, Integer groupNum) {
        this.groupDate = groupDate;
        this.groupNum = groupNum;
    }

    public GroupInfo() {
    }
}