package cn.haizhi.market.main.bean.madao;

import cn.haizhi.market.other.enums.madao.CommentStatusEnum;
import cn.haizhi.market.other.enums.madao.DeliveryStatusEnum;
import cn.haizhi.market.other.enums.madao.OrderStatusEnum;
import cn.haizhi.market.other.enums.madao.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PgOrderMaster {
    private String orderId;

    private String groupId;

    private Long userId;

    private String userName;

    private String userPhone;

    private String userAddress;

    private BigDecimal orderAmount;

    private Byte orderStatus = OrderStatusEnum.NEW.getCode();

    private Byte payStatus = PayStatusEnum.WAIT.getCode();

    private Byte deliveryStatus = DeliveryStatusEnum.WAIT.getCode();

    private Byte commentStatus = CommentStatusEnum.WAIT.getCode();

    private Byte payWay;

    private String payAcount;

    private Date deliveryTime;

    private Date arriveTime;

    private Date receiveTime;

    private Long commentId;

    private Date createTime;

    private Date updateTime;

    private String orderRemark;
}