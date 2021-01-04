package com.buko.db.designticketingsystem.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buko.db.designticketingsystem.enumerate.impl.CredentialsTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表[t_user]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Data
@TableName(value = "`t_user`")
@ApiModel(value = "表[t_user]的实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    /**
     * 用户编号：自增，主键
     */
    @ApiModelProperty(value = "用户编号：自增，主键", dataType = "Long")
    @TableField("`id`")
    @TableId(value = "`id`", type = IdType.AUTO)
    private Long id;

    /**
     * 用户真实姓名：非空
     */
    @ApiModelProperty(value = "用户真实姓名：非空", dataType = "String")
    @TableField("`real_name`")
    private String realName;

    /**
     * 用户证件号：非空，唯一
     */
    @ApiModelProperty(value = "用户证件号：非空，唯一", dataType = "String")
    @TableField("`credentials`")
    private String credentials;

    /**
     * 用户证件类型：默认身份证
     */
    @ApiModelProperty(value = "用户证件类型：默认身份证", dataType = "int")
    @TableField("`credentials_type`")
    private CredentialsTypeEnum credentialsType;

    /**
     * 用户账号名：非空，唯一
     */
    @ApiModelProperty(value = "用户账号名：非空，唯一", dataType = "String")
    @TableField("`username`")
    private String username;

    /**
     * 用户密码：非空，密文
     */
    @ApiModelProperty(value = "用户密码：非空，密文", dataType = "String")
    @TableField("`password`")
    private String password;

    /**
     * 联系方式：非空，唯一，13位
     */
    @ApiModelProperty(value = "联系方式：非空，唯一，13位", dataType = "String")
    @TableField("`phone_number`")
    private String phoneNumber;

    /**
     * 创建时间：默认当前时间
     */
    @ApiModelProperty(value = "创建时间：默认当前时间", dataType = "java.util.Date")
    @TableField("`created_time`")
    private Date createdTime;
}