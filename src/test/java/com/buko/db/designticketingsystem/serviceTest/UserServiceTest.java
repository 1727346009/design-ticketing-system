package com.buko.db.designticketingsystem.serviceTest;

import com.buko.db.designticketingsystem.DesignTicketingSystemApplication;
import com.buko.db.designticketingsystem.po.User;
import com.buko.db.designticketingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = DesignTicketingSystemApplication.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void userExistOrNot() {
        log.info(String.valueOf(userService.userExistOrNot("")));
    }

    @Test
    public void authUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123456");
        log.info(String.valueOf(userService.loginUser(user, "192.168.1.161")));
    }
}
