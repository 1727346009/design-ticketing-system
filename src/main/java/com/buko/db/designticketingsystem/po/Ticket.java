package com.buko.db.designticketingsystem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buko.db.designticketingsystem.enumerate.impl.CabinClassEnum;
import com.buko.db.designticketingsystem.enumerate.impl.PreSaleStatusEnum;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表[t_ticket]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Data
@TableName(value = "`t_ticket`")
@ApiModel(value = "表[t_ticket]的实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket implements Serializable {

    /**
     * 机票编号：自增，主键
     */
    @ApiModelProperty(value = "机票编号：自增，主键", dataType = "Long")
    @TableField("`id`")
    @TableId(value = "`id`", type = IdType.AUTO)
    private Long id;

    /**
     * 票价：非空，大于0
     */
    @ApiModelProperty(value = "票价：非空，大于0", dataType = "int")
    @TableField("`price`")
    @Min(value = 0, message = "价格异常", groups = {CommonValidGroup.Insert.class})
    private Integer price;

    /**
     * 折扣：大于0.0，小于2.0
     */
    @ApiModelProperty(value = "折扣：大于0.0，小于2.0", dataType = "BigDecimal")
    @TableField("`discount`")
    @Max(value = 2, message = "折扣异常", groups = {CommonValidGroup.Insert.class})
    private BigDecimal discount;

    /**
     * 余票：大于0
     */
    @ApiModelProperty(value = "余票：大于0", dataType = "int")
    @TableField("`surplus`")
    @Min(value = 0, message = "余票异常", groups = {CommonValidGroup.Insert.class})
    private Integer surplus;

    /**
     * 机舱等级：默认经济舱
     */
    @ApiModelProperty(value = "机舱等级：默认经济舱", dataType = "int")
    @TableField("`cabin_class`")
    private CabinClassEnum cabinClass;

    /**
     * 预售状态：默认预售中
     */
    @ApiModelProperty(value = "预售状态：默认预售中", dataType = "int")
    @TableField("`pre_sale_status`")
    private PreSaleStatusEnum preSaleStatus;

    /**
     * 航班编号：参照t_flight 表主键
     */
    @ApiModelProperty(value = "航班编号：参照t_flight 表主键", dataType = "Long")
    @TableField("`flight_id`")
    @NotNull(message = "航班编号非空", groups = {CommonValidGroup.Insert.class})
    private Long flightId;

    /**
     * 业务员工号：参照t_manager 表主键
     */
    @ApiModelProperty(value = "业务员工号：参照t_manager 表主键", dataType = "Long")
    @TableField("`manager_id`")
    private Long managerId;

    /**
     * 创建时间：默认当前时间
     */
    @ApiModelProperty(value = "创建时间：默认当前时间", dataType = "java.util.Date")
    @TableField("`created_time`")
    private Date createdTime;

}