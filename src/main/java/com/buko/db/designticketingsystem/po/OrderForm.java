package com.buko.db.designticketingsystem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buko.db.designticketingsystem.enumerate.impl.OrderFormStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表[t_order_form]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Data
@TableName(value = "`t_order_form`")
@ApiModel(value = "表[t_order_form]的实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForm implements Serializable {

    /**
     * 订单号：BK+航班编号+用户编号
     */
    @ApiModelProperty(value = "订单号：BK用户编号f航班编号t机票编号", dataType = "String")
    @TableField("`id`")
    @TableId(value = "`id`", type = IdType.INPUT)
    private String id;

    /**
     * 乘客编号：参考客户信息乘客编号
     */
    @ApiModelProperty(value = "乘客编号：参考客户信息乘客编号", dataType = "Long")
    @TableField("`user_id`")
    private Long userId;

    /**
     * 机票编号：参考机票信息机票编号
     */
    @ApiModelProperty(value = "机票编号：参考机票信息机票编号", dataType = "Long")
    @TableField("`ticket_id`")
    private Long ticketId;

    /**
     * 支付金额：非空，大于0
     */
    @ApiModelProperty(value = "支付金额：非空，大于0", dataType = "BigDecimal")
    @TableField("`payment_amount`")
    private BigDecimal paymentAmount;

    /**
     * 订单状态：非空，默认未支付
     */
    @ApiModelProperty(value = "支付状态：非空，默认未支付", dataType = "int")
    @TableField("`ticket_status`")
    private OrderFormStatusEnum orderFormStatus;

    /**
     * 创建时间：默认当前时间
     */
    @ApiModelProperty(value = "创建时间：默认当前时间", dataType = "java.util.Date")
    @TableField("`created_time`")
    private Date createdTime;
}