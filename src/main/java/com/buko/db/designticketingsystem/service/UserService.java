package com.buko.db.designticketingsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buko.db.designticketingsystem.po.Token;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.vo.ShowUserVO;

/**
 * User对应Service
 *
 * @author buko 2020年12月03日
 */
public interface UserService extends IService<User> {

    /**
     * 用户是否存在
     *
     * @param username 用户名
     * @return exist ? true : false
     */
    boolean userExistOrNot(String username);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean createUser(User user);

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @param host 主机
     * @return token
     */
    Token loginUser(User user, String host);

    /**
     * 获取 user 视图
     *
     * @param username username
     * @return user
     */
    ShowUserVO getUserVOByUsername(String username);

    /**
     * 获取 user 视图
     *
     * @param id id
     * @return user
     */
    ShowUserVO getUserVOById(Long id);

    /**
     * users 分页模糊查询
     *
     * @param page 分页器
     * @param key  关键字
     * @return users
     */
    IPage<ShowUserVO> selectUserPageVO(Page<ShowUserVO> page, String key);

    /**
     * update password
     *
     * @param id          id
     * @param oldPassword oldPassword
     * @param newPassword newPassword
     * @return boolean
     */
    boolean updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * update phone number
     *
     * @param id             id
     * @param code           code
     * @param newPhoneNumber new phone number
     * @return boolean
     */
    boolean updatePhoneNumber(Long id, String code, String newPhoneNumber);

    /**
     * forger password
     *
     * @param code        code
     * @param PhoneNumber phone number
     * @param newPassword new password
     * @return boolean
     */
    boolean forgetPassword(String code, String PhoneNumber, String newPassword);
}