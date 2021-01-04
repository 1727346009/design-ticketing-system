package com.buko.db.designticketingsystem.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.buko.db.designticketingsystem.dto.RequestResult;
import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.buko.db.designticketingsystem.exception.AuthenticateException;
import com.buko.db.designticketingsystem.exception.BaseException;
import com.buko.db.designticketingsystem.exception.NoAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author Mr.徐健威
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {BaseException.class})
    public RequestResult<String> baseHandler(RuntimeException e) {
        return new RequestResult<>(StatusCodeEnum.DOES_NOT_EXIST, e.getMessage());
    }

    @ExceptionHandler(value = {JWTVerificationException.class})
    public RequestResult<String> jwtHandler(JWTVerificationException e) {
        log.debug(e.getMessage());
        return new RequestResult<>(StatusCodeEnum.FORBIDDEN);
    }

    @ExceptionHandler(value = {NoAccessException.class})
    public RequestResult<String> accessHandler(NoAccessException e) {
        return new RequestResult<>(StatusCodeEnum.FORBIDDEN);
    }

    @ExceptionHandler(value = {AuthenticateException.class})
    public RequestResult<String> authHandler(AuthenticateException e) {
        return new RequestResult<>(StatusCodeEnum.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public RequestResult<String> checkHandler(Exception e) {
        log.debug(e.getMessage());
        return new RequestResult<>(StatusCodeEnum.SYS_INNER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public RequestResult<String> validHandle(ConstraintViolationException e) {
        return new RequestResult<>(StatusCodeEnum.DOES_NOT_EXIST, e.getMessage());
    }
}
