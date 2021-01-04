package com.buko.db.designticketingsystem.serviceTest;

import com.buko.db.designticketingsystem.DesignTicketingSystemApplication;
import com.buko.db.designticketingsystem.service.TicketService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = DesignTicketingSystemApplication.class)
public class TicketServiceTest {
    @Resource
    private TicketService ticketService;
}
