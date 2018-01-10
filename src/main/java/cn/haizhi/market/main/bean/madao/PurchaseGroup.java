package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseGroup {
    private String groupId;

    private Integer groupNum;

    private Integer groupCount;

    private Byte groupStatus;

    private Date createTime;

    private Date updateTime;
}