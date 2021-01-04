package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buko.commons.util.JWTUtil;
import com.buko.db.designticketingsystem.dao.ManagerDao;
import com.buko.db.designticketingsystem.enumerate.impl.PermissionEnum;
import com.buko.db.designticketingsystem.enumerate.PowerRoleEnum;
import com.buko.db.designticketingsystem.exception.BaseException;
import com.buko.db.designticketingsystem.po.Manager;
import com.buko.db.designticketingsystem.po.Token;
import com.buko.db.designticketingsystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager对应service实现
 *
 * @author buko 2020年12月03日
 */
@Service
@DS("super")
@Transactional(rollbackFor = Exception.class)
public class ManagerServiceImpl extends ServiceImpl<ManagerDao, Manager> implements ManagerService {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private JWTUtil jwtUtil;
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Manager getManagerById(Long id) {
        return managerDao.getManagerById(id);
    }

    @Override
    public Token loginManager(Manager manager) {
        log.debug("service: manager try to sign in :" + manager.getUsername());
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "password", "permission");
        queryWrapper.eq("username", manager.getUsername());
        Manager auth = managerDao.selectOne(queryWrapper);
        log.debug(auth.toString());
        if (auth.getPermissions().equals(PermissionEnum.DISABLE)) {
            throw new BaseException("该管理员已被禁用");
        }
        // 验证密码
        if (!encoder.matches(manager.getPassword(), auth.getPassword())) {
            throw new BaseException("用户名或密码错误");
        }
        // 添加 token 验证头部
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("id", auth.getId().toString());
        claims.put("role", PowerRoleEnum.USER.getName());
        Token token = new Token();
        token.setAuthenticate(jwtUtil.generateJWT(claims, secret));
        return token;
    }
}