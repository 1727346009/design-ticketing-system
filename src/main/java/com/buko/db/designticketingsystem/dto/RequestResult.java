package com.buko.db.designticketingsystem.dto;

import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Mr.徐健威
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResult<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * 无参构造器，只返回请求成功的ack
     * 在无返回数据，且请求成功时使用
     */
    public RequestResult() {
        this.code = StatusCodeEnum.SUCCESS.getCode();
        this.message = StatusCodeEnum.SUCCESS.getName();
    }

    /**
     * 无返回数据，且请求不成功时使用
     *
     * @param status 状态枚举
     */
    public RequestResult(StatusCodeEnum status) {
        this.code = status.getCode();
        this.message = status.getName();
    }

    /**
     * 常规构造器
     *
     * @param status 状态枚举
     * @param data   返回数据
     */
    public RequestResult(StatusCodeEnum status, T data) {
        this.code = status.getCode();
        this.message = status.getName();
        this.data = data;
    }
}
