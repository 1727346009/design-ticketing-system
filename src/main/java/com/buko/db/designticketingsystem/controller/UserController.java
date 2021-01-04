package com.buko.db.designticketingsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buko.db.designticketingsystem.annotation.PassToken;
import com.buko.db.designticketingsystem.annotation.power.ManagerPower;
import com.buko.db.designticketingsystem.annotation.power.UserPower;
import com.buko.db.designticketingsystem.dto.RequestResult;
import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.service.UserService;
import com.buko.db.designticketingsystem.vo.ShowUserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author buko
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过用户名查询用户，需要管理员权限
     *
     * @param username path variable username
     * @return show user view
     */
    @ManagerPower
    @GetMapping(value = "/user/{username}")
    public RequestResult<ShowUserVO> getUser(@PathVariable String username) {
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                userService.getUserVOByUsername(username)
        );
    }

    /**
     * 用户注册
     *
     * @param user user
     * @return 是否注册成功
     */
    @PassToken
    @PostMapping(value = "/user")
    public RequestResult<String> registerUser(@RequestBody User user) {
        userService.createUser(user);
        return new RequestResult<>();
    }

    /**
     * 已登录用户获取自身信息
     *
     * @param id 通过解析 token 获取
     * @return show user view
     */
    @UserPower
    @GetMapping(value = "/user/info")
    public RequestResult<ShowUserVO> getUserInfo(@RequestAttribute Long id) {
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                userService.getUserVOById(id));
    }

    /**
     * 管理员查看搜索用户列表
     *
     * @param pageSize   页面大小
     * @param pageNumber 当前页数
     * @param key        关键词
     * @return show users view
     */
    @ManagerPower
    @GetMapping(value = "/users")
    public RequestResult<IPage<ShowUserVO>> getUsersPageVo(@RequestParam("ps") Integer pageSize,
                                                           @RequestParam("pn") Integer pageNumber,
                                                           @RequestParam("key") String key) {
        Page<ShowUserVO> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNumber);
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                userService.selectUserPageVO(page, key)
        );
    }

    /**
     * 用户通过原密码修改密码，需要登陆
     *
     * @param id          id 通过解析 token 获取
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 成功信息
     */
    @UserPower
    @PutMapping(value = "/user/p")
    public RequestResult<String> updatePassword(@RequestAttribute("id") Long id,
                                                @RequestParam("old") String oldPassword,
                                                @RequestParam("new") String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return new RequestResult<>();
    }

    /**
     * 用户通过验证码修改手机号，需要登录
     *
     * @param id             id
     * @param code           验证码
     * @param newPhoneNumber 新手机号
     * @return 成功信息
     */
    @UserPower
    @PutMapping(value = "/user/n")
    public RequestResult<String> updatePhoneNumber(@RequestAttribute("id") Long id,
                                                   @RequestParam("code") String code,
                                                   @RequestParam("new") String newPhoneNumber) {
        userService.updatePhoneNumber(id, code, newPhoneNumber);
        return new RequestResult<>();
    }

    /**
     * 用户忘记密码，通过手机号重设密码
     * @param code 验证码
     * @param phoneNumber 手机号码
     * @param newPassword 新密码
     * @return 成功信息
     */
    @UserPower
    @PutMapping(value = "/user/f")
    public RequestResult<String> forgetPassword(@RequestParam("code") String code,
                                                @RequestParam("phoneNumber") String phoneNumber,
                                                @RequestParam("new") String newPassword) {
        userService.forgetPassword(code, phoneNumber, newPassword);
        return new RequestResult<>();
    }
}
