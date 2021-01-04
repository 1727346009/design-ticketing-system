package com.buko.db.designticketingsystem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buko.db.designticketingsystem.enumerate.impl.FlightStatusEnum;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import com.buko.db.designticketingsystem.validation.FlightValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 表[t_flight]对应实体类
 * 
 * @author buko 2020年12月09日 
 */
@Data
@TableName(value = "`t_flight`")
@ApiModel(value = "表[t_flight]的实体类")
public class Flight {

    /**
     * 航班编号：自增，主键
     */
    @ApiModelProperty(value = "航班编号：自增，主键", dataType = "Long")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    @NotNull(message = "航班编号不能为空",
            groups = {FlightValidGroup.Delay.class, CommonValidGroup.Update.class, CommonValidGroup.Select.class})
    private Long id;

    /**
     * 业务员工号：参照 t_manager 表主键
     */
    @ApiModelProperty(value = "业务员工号：参照 t_manager 表主键", dataType = "Long")
    @TableField("`manager_id`")
    @NotNull(message = "业务员编号不能为空", groups = {CommonValidGroup.Insert.class})
    private Long managerId;

    /**
     * 飞机编号：非空
     */
    @ApiModelProperty(value = "飞机编号：非空", dataType = "String")
    @TableField("`aircraft_name`")
    @NotNull(message = "飞机编号不能为空", groups = {CommonValidGroup.Insert.class})
    @NotBlank(message = "飞机编号不能为空", groups = {CommonValidGroup.Insert.class})
    private String aircraftName;

    /**
     * 出发地：非空
     */
    @ApiModelProperty(value = "出发地：非空", dataType = "String")
    @TableField("`starting_city`")
    @NotNull(message = "出发地不能为空", groups = {CommonValidGroup.Insert.class})
    @NotBlank(message = "出发地不能为空", groups = {CommonValidGroup.Insert.class})
    private String startingCity;

    /**
     * 起飞机场：非空
     */
    @ApiModelProperty(value = "起飞机场：非空", dataType = "String")
    @TableField("`departure_airport`")
    @NotNull(message = "起飞机场不能为空", groups = {CommonValidGroup.Insert.class})
    @NotBlank(message = "起飞机场不能为空", groups = {CommonValidGroup.Insert.class})
    private String departureAirport;

    /**
     * 目的地：非空
     */
    @ApiModelProperty(value = "目的地：非空", dataType = "String")
    @TableField("`destination_city`")
    @NotNull(message = "目的地不能为空", groups = {CommonValidGroup.Insert.class})
    @NotBlank(message = "目的地不能为空", groups = {CommonValidGroup.Insert.class})
    private String destinationCity;

    /**
     * 落地机场：非空
     */
    @ApiModelProperty(value = "落地机场：非空", dataType = "String")
    @TableField("`landing_airport`")
    @NotNull(message = "落地机场不能为空", groups = {CommonValidGroup.Insert.class})
    @NotBlank(message = "落地机场不能为空", groups = {CommonValidGroup.Insert.class})
    private String landingAirport;

    /**
     * 起飞时间：非空，大于当前时间
     */
    @ApiModelProperty(value = "起飞时间：非空，大于当前时间", dataType = "Long")
    @TableField("`departure_time`")
    @NotNull(message = "起飞时间不能为空",
            groups = {FlightValidGroup.Delay.class, CommonValidGroup.Select.class, CommonValidGroup.Insert.class})
    private Long departureTime;

    /**
     * 预计行程时间：非空，单位分钟
     */
    @ApiModelProperty(value = "预计行程时间：非空，单位分钟", dataType = "int")
    @TableField("`duration`")
    @NotNull(message = "预计行程时间不能为空", groups = {CommonValidGroup.Insert.class})
    @Size(min = 10, max = 1440, message = "行程时间异常", groups = {CommonValidGroup.Insert.class, CommonValidGroup.Update.class})
    private Integer duration;

    /**
     * 航班状态：默认为未起飞
     */
    @ApiModelProperty(value = "航班状态：默认为未起飞", dataType = "int")
    @TableField("`flight_status`")
    private FlightStatusEnum flightStatus;

    /**
     * 创建时间：默认当前时间
     */
    @ApiModelProperty(value = "创建时间：默认当前时间", dataType = "java.util.Date")
    @TableField("`created_time`")
    private java.util.Date createdTime;

}