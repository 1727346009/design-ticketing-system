package com.buko.db.designticketingsystem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buko.db.designticketingsystem.enumerate.impl.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表[t_manager]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Data
@TableName(value = "`t_manager`")
@ApiModel(value = "表[t_manager]的实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Manager implements Serializable {

    /**
     * 业务员工号：自增，主键
     */
    @ApiModelProperty(value = "业务员工号：自增，主键", dataType = "Long")
    @TableField("`id`")
    @TableId(value = "`id`", type = IdType.AUTO)
    private Long id;

    /**
     * 真实姓名：非空
     */
    @ApiModelProperty(value = "真实姓名：非空", dataType = "String")
    @TableField("`real_name`")
    private String realName;

    /**
     * 账号：非空，唯一
     */
    @ApiModelProperty(value = "账号：非空，唯一", dataType = "String")
    @TableField("`username`")
    private String username;

    /**
     * 密码：非空，加密
     */
    @ApiModelProperty(value = "密码：非空，加密", dataType = "String")
    @TableField("`password`")
    private String password;

    /**
     * 权限：非空，默认为普通
     */
    @ApiModelProperty(value = "权限：非空，默认为普通", dataType = "int")
    @TableField("`permissions`")
    private PermissionEnum permissions;

    /**
     * 创建时间：默认当前时间
     */
    @ApiModelProperty(value = "创建时间：默认当前时间", dataType = "java.util.Date")
    @TableField("`created_time`")
    private Date createdTime;

}