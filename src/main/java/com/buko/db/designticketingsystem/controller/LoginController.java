package com.buko.db.designticketingsystem.controller;

import com.buko.db.designticketingsystem.annotation.PassToken;
import com.buko.db.designticketingsystem.annotation.SignIn;
import com.buko.db.designticketingsystem.dto.RequestResult;
import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.buko.db.designticketingsystem.po.Manager;
import com.buko.db.designticketingsystem.po.Token;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.service.ManagerService;
import com.buko.db.designticketingsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Mr.徐健威
 */
@RequestMapping(produces = "application/json;charset=UTF-8")
@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private ManagerService managerService;

    /**
     * 用户登录
     * @param user 用户（账号-密码）
     * @param host 请求登录 ip address
     * @return token 明细
     */
    @SignIn
    @PostMapping(value = "/login")
    public RequestResult<Token> userSignIn(@RequestBody User user,
                                           @RequestAttribute("host") String host) {
        Token token = userService.loginUser(user, host);
        return new RequestResult<>(StatusCodeEnum.SUCCESS, token);
    }

    /**
     * 管理员登录
     * @param manager 管理员（账号-密码）
     * @return token 明细
     */
    @PassToken
    @PostMapping(value = "/manager/login")
    public RequestResult<Token> managerSignIn(@RequestBody Manager manager) {
        Token token = managerService.loginManager(manager);
        return new RequestResult<>(StatusCodeEnum.SUCCESS, token);
    }
}
