package cn.haizhi.market.other.enums.madao;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    PARAM_ERROR(403, "参数不正确"),

    CARTITEM_NOT_EXIST(404, "购物车项不存在"),
    CARTITEM_DELETE_FAIL(405, "购物车项删除失败"),

    PRODUCT_INFO_ERROR(406, "商品信息错误"),
    PRODUCT_CATEGORY_ERROR(407, "商品基本类别错误"),
    PRODUCT_NOT_EXIST(408, "商品不存在"),
    PRODUCT_DOWN(409, "商品已下架"),
    PRODUCT_STOCK_ERROR(410, "商品库存不足"),



    CREATE_ORDER_FAIL(411, "未添加订单"),
    CARTITEM_OWNER_ERROR(412, "购物车项和用户不匹配"),
    ORDER_LIMIT_ERROR(413, "订单未超过商家最低配送额"),

    PAY_ORDER_ERROR(414, "未支付订单"),
    ORDER_STATUS_ERROR(415, "订单状态不正确"),
    ORDER_PAY_STATUS_ERROR(416, "订单支付状态不正确"),

    ORDER_OWNER_ERROR(417, "订单不属于该用户"),
    ORDER_SHOP_OWNER_ERROR(418, "订单不属于该商家"),
    CARTITEM_CATEGORY_ERROR(419, "该订单不属于拼购订单"),

    ORDER_HAD_COMMENT(420, "订单已评价"),
    ORDER_NOT_FINISH(421, "订单未完成"),
    ORDER_NOT_EXIST(422, "订单不存在"),



    ADDRESS_ERROR(423, "用户地址不匹配"),
    CARTITEM_NOT_PLAIN(424, "购物车项不属于普通商品购物车"),
    CARTITEM_NO_PG(425, "购物车项不属于拼购购物车"),
//    PRODUCT_GAIN_ERROR(31, "获取商品信息错误"),

    ORDER_CREATE_ERROR(426, "订单创建失败"),
    ORDER_PAY_FAIL(427, " 订单支付失败"),

    ORDER_CANCEL_FAIL(428, "订单取消失败"),
    ORDER_NOT_CANCEL(429, "订单未取消"),

    GROUP_INFO_ERROR(430, "商家未设置拼购组信息"),
    GROUP_NOT_EXIST(431, "拼购组不存在"),
    GROUP_NOT_ACTIVE(432, "拼购组已过期"),
    GROUP_GROUP_FULL(433, "拼购组已满"),

    ORDER_HAD_DELIVERY(432, "订单已配送"),
    SET_DELIVERY_TIME_FAIL(433, "设置配送时间失败"),
    ORDER_HAD_OVERDUE(434, "订单已经过了退货时间"),
    PG_GROUP_HAN_OVERDUE(435, "拼购组已失效"),
    SHOP_CLOSE(436, "商店已关闭"),

    CONFIRM_CANCEL_FAIL(437, "确认取消订单失败"),
    CONFIRM_RECEIVE_FAIL(438, "确认收货失败"),
    USER_HAVE_ORDER_IN_GROUP(439, "用户已经有订单在该组中"),
    ;

    private int code;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;

}
