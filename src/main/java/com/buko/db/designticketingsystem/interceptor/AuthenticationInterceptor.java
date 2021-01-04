package com.buko.db.designticketingsystem.interceptor;

import com.buko.commons.util.IPUtils;
import com.buko.commons.util.JWTUtil;
import com.buko.db.designticketingsystem.annotation.PassToken;
import com.buko.db.designticketingsystem.annotation.SignIn;
import com.buko.db.designticketingsystem.annotation.power.ManagerPower;
import com.buko.db.designticketingsystem.annotation.power.UserPower;
import com.buko.db.designticketingsystem.enumerate.PowerRoleEnum;
import com.buko.db.designticketingsystem.exception.AuthenticateException;
import com.buko.db.designticketingsystem.exception.NoAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

import static com.buko.commons.constant.Profiles.DEV;
import static com.buko.commons.constant.Profiles.TEST;

/**
 * @author Mr.徐健威
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private IPUtils ipUtils;
    @Resource
    private JWTUtil jwtUtil;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${spring.profiles.active}")
    private String profiles;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (DEV.equals(profiles)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        String requestIP = ipUtils.getIpAddr(request);
        log.info("request ip address is " + requestIP);

        if (method.isAnnotationPresent(SignIn.class)) {
            SignIn signIn = method.getAnnotation(SignIn.class);
            if (signIn.required()) {
                request.setAttribute("host", requestIP);
                return true;
            }
        }
        // 验证 token
        if (token == null || "".equals(token)) {
            throw new AuthenticateException("请重新登录");
        }
        Map<String, Object> claims = jwtUtil.parseJWT(secret, token);
        // 验证 id
        String id = (String) claims.get("id");
        idHandle(id);
        request.setAttribute("id", id);
        // 验证权限
        String role = (String) claims.get("role");
        roleHandle(role, method);
        if (role.equals(PowerRoleEnum.MANAGER.getName())) {
            return true;
        }
        // 验证 ip
        String tokenIP = (String) claims.get("host");
        ipHandle(id, tokenIP, requestIP);
        return true;
    }

    public void idHandle(String id) {
        log.debug("request id is " + id);
        if ("".equals(id)) {
            throw new AuthenticateException("请重新登录");
        }
    }

    public void ipHandle(String key, String tokenIP, String requestIP) {
        if (!requestIP.equals(tokenIP)) {
            throw new AuthenticateException("请重新登录");
        }
        String cache = redisTemplate.opsForValue().get("user:" + key);
        log.debug("request host cache is " + cache);
        if (!requestIP.equals(cache)) {
            throw new AuthenticateException("请重新登录");
        }
    }

    public void roleHandle(String role, Method method) {
        log.debug("request role is " + role);
        if (role == null) {
            throw new AuthenticateException("请重新登录");
        }
        if (TEST.equals(profiles)) {
            return;
        }

        if (method.isAnnotationPresent(ManagerPower.class)) {
            ManagerPower managerPower = method.getAnnotation(ManagerPower.class);
            if (!managerPower.required() || !managerPower.getRole().equals(role)) {
                throw new NoAccessException("您无权访问该信息");
            } else {
                return;
            }
        }

        if (method.isAnnotationPresent(UserPower.class)) {
            UserPower userPower = method.getAnnotation(UserPower.class);
            if (!userPower.required() || !userPower.getRole().equals(role)) {
                throw new NoAccessException("您无权访问该信息");
            }
        }
    }
}
