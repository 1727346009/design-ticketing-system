package com.buko.db.designticketingsystem.serviceTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buko.db.designticketingsystem.DesignTicketingSystemApplication;
import com.buko.db.designticketingsystem.service.OrderFormService;
import com.buko.db.designticketingsystem.vo.ShowOrderFormVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = DesignTicketingSystemApplication.class)
public class OrderFormServiceTest {
    @Resource
    private OrderFormService orderFormService;

    @Test
    public void getOrderForms() {
        Page<ShowOrderFormVO> page = new Page<>();
        page.setCurrent(0);
        page.setSize(10);
        log.info(String.valueOf(orderFormService.getOrderForms(page, 1L).getRecords()));
    }
}
