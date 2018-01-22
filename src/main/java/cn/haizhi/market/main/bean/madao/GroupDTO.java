package cn.haizhi.market.main.bean.madao;

import cn.haizhi.market.other.enums.madao.GroupActiveStatusEnum;
import cn.haizhi.market.other.enums.madao.GroupStatusEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GroupDTO {
    private String groupId;

    private String leadMemberId;

    private Integer groupNum;

    private Integer groupCount;

    private Byte groupStatus;

    private Date deadDate;

    private Byte activeStatus;

    private Date createTime;

    private Date updateTime;

    private List<GroupMember> groupMemberList;
}
