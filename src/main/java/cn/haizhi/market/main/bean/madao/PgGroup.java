package cn.haizhi.market.main.bean.madao;

import lombok.Data;

import java.util.Date;

@Data
public class PgGroup {
    private String groupId;

    private String leadMemberId;

    private Long userId;

    private Integer groupNum;

    private Integer groupCount;

    private Byte groupStatus;

    private Date deadDate;

    private Byte activeStatus;

    private Date createTime;

    private Date updateTime;

}