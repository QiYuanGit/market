package cn.haizhi.market.main.mapper.madao;

import cn.haizhi.market.main.bean.madao.GroupMember;
import cn.haizhi.market.main.bean.madao.GroupMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMemberMapper {
    long countByExample(GroupMemberExample example);

    int deleteByExample(GroupMemberExample example);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    List<GroupMember> selectByExample(GroupMemberExample example);

    int updateByExampleSelective(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByExample(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);
}