package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buko.commons.util.JWTUtil;
import com.buko.db.designticketingsystem.dao.UserDao;
import com.buko.db.designticketingsystem.enumerate.PowerRoleEnum;
import com.buko.db.designticketingsystem.po.Token;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.service.UserService;
import com.buko.db.designticketingsystem.vo.ShowUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * User对应service实现
 *
 * @author buko 2020年12月03日
 */
@Service
@Slf4j
@DS("master_user")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private UserDao userDao;
    @Resource
    private JWTUtil jwtUtil;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.secret}")
    private String secret;
    @Lazy
    @Resource
    private UserService userService;

    @Override
    public boolean userExistOrNot(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("1");
        wrapper.eq("username", username).last("limit 1");
        log.debug("service: user is exist or not :" + username);
        return userDao.selectCount(wrapper) != 0;
    }

    @Override
    public boolean createUser(User user) {
        if (userService.userExistOrNot(user.getUsername())) {
            throw new RuntimeException("用户名已被注册");
        }
        log.debug("service: user try to sign up :" + user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return true;
    }

    @Override
    public Token loginUser(User user, String host) {
        log.debug("service: user try to sign in :" + user.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "password");
        queryWrapper.eq("username", user.getUsername());
        User auth = userDao.selectOne(queryWrapper);
        log.debug(auth.toString());
        // 验证密码
        if (!encoder.matches(user.getPassword(), auth.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 设置 token cache
        redisTemplate.opsForValue().set("user:" + auth.getId(), host,
                jwtUtil.getJwtConfig().getExpiresSeconds(), TimeUnit.MILLISECONDS);
        // 添加 token 验证头部
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("id", auth.getId().toString());
        claims.put("role", PowerRoleEnum.USER.getName());
        claims.put("host", host);
        Token token = new Token();
        token.setAuthenticate(jwtUtil.generateJWT(claims, secret));
        return token;
    }

    @Override
    public ShowUserVO getUserVOByUsername(String username) {
        log.debug("service: select user view by username");
        return userDao.getUserVoByUsername(username);
    }

    @Override
    public ShowUserVO getUserVOById(Long id) {
        log.debug("service: select user view by id");
        return userDao.getUserVOById(id);
    }

    @Override
    public IPage<ShowUserVO> selectUserPageVO(Page<ShowUserVO> page, String key) {
        log.debug("service: select user page view");
        return userDao.selectUserPageVo(page, key);
    }

    @Override
    public boolean updatePassword(Long id, String oldPassword, String newPassword) {
        log.debug("service: update password from id :" + id);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("password")
                .eq("id", id);
        User auth = userDao.selectOne(queryWrapper);
        // 验证密码
        if (!encoder.matches(oldPassword, auth.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set(true, "password", encoder.encode(newPassword))
                .eq("id", id);
        userService.update(updateWrapper);
        return true;
    }

    @Override
    public boolean updatePhoneNumber(Long id, String code, String newPhoneNumber) {
        log.debug("service: update phone number from id :" + id);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set(true, "phone_number", newPhoneNumber)
                .eq("id", id);
        userService.update(updateWrapper);
        return true;
    }

    @Override
    public boolean forgetPassword(String code, String PhoneNumber, String newPassword) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set(true, "password", newPassword)
                .eq("phone_number", PhoneNumber);
        userService.update(updateWrapper);
        return true;
    }

}