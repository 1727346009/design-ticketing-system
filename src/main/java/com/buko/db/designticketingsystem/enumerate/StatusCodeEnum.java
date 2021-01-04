package com.buko.db.designticketingsystem.enumerate;

/**
 * @author Mr.徐健威
 */

public enum StatusCodeEnum {
    /**
     * success
     * 请求成功
     */
    SUCCESS("请求成功", 200),
    /**
     * bad request
     * 语法参数错误
     */
    BAD_REQUEST("语法参数错误", 400),
    /**
     * unauthorized
     * 需要身份认证
     */
    UNAUTHORIZED("需要身份认证", 401),
    /**
     * forbidden
     * 拒绝请求
     */
    FORBIDDEN("拒绝请求", 403),
    /**
     * dose not exist
     * 请求资源不存在
     */
    DOES_NOT_EXIST("请求资源不存在", 404),
    /**
     * method not allowed
     * 方法被禁止
     */
    METHOD_NOT_ALLOWED("方法被禁止", 405),
    /**
     * sys inner error
     * 服务器内部错误
     */
    SYS_INNER_ERROR("服务器内部错误", 500);

    private final String name;

    private final Integer code;

    StatusCodeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
