package cn.haizhi.market.main.bean.madao;

import jdk.internal.instrumentation.InstrumentationMethod;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class GroupInfo {
    private String groupInfoId;

    @NotNull(message = "拼购商品数不能为空")
    @Min(value = 0, message="拼购商品数不能小于0")
    private Integer groupNum;

    private Date groupDate;

    public GroupInfo() {
    }

    public GroupInfo(String groupInfoId, Integer groupNum, Date groupDate) {
        this.groupInfoId = groupInfoId;
        this.groupNum = groupNum;
        this.groupDate = groupDate;
    }
}