package com.buko.db.designticketingsystem;

import com.buko.commons.util.IPUtils;
import com.buko.commons.util.JWTUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * @author 徐健威
 */
@SpringBootApplication(scanBasePackages = {"com.buko.db.designticketingsystem"})
@MapperScan(basePackages = "com.buko.db.designticketingsystem.dao")
public class DesignTicketingSystemApplication extends SpringBootServletInitializer {
    private final RedisTemplate<?, ?> redisTemplate;

    @Autowired
    public DesignTicketingSystemApplication(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DesignTicketingSystemApplication.class, args);
    }

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer<?> serializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
    }

    @Bean
    public IPUtils ipUtil() {
        return new IPUtils();
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DesignTicketingSystemApplication.class);
    }
}
