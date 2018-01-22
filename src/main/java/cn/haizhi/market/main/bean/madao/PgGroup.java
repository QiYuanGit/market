package cn.haizhi.market.main.bean.madao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@DynamicUpdate
public class PgGroup {
    private String groupId;

    private String leadMemberId;

    private Integer groupNum;

    private Integer groupCount;

    private Byte groupStatus;

    private Date deadDate;

    private Byte activeStatus;

    private Date createTime;

    private Date updateTime;

    public String getGroupId() {
        return groupId;
    }
}