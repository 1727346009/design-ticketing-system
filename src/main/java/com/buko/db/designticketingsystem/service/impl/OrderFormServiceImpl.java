package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buko.db.designticketingsystem.dao.OrderFormDao;
import com.buko.db.designticketingsystem.exception.BaseException;
import com.buko.db.designticketingsystem.po.OrderForm;
import com.buko.db.designticketingsystem.service.FlightService;
import com.buko.db.designticketingsystem.service.OrderFormService;
import com.buko.db.designticketingsystem.service.TicketService;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import com.buko.db.designticketingsystem.vo.ShowOrderFormVO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * OrderForm对应service实现
 *
 * @author buko 2020年12月03日
 */
@Service
@Validated
@DS("master_user")
public class OrderFormServiceImpl extends ServiceImpl<OrderFormDao, OrderForm> implements OrderFormService {
    @Resource
    private TicketService ticketService;
    @Resource
    private FlightService flightService;
    @Resource
    private OrderFormDao orderFormDao;
    @Lazy
    @Resource
    private OrderFormService orderFormService;

    @Override
    @Validated({CommonValidGroup.Insert.class})
    public boolean saleTicket(@Valid OrderForm orderForm, Long flightId) {
        if (flightService.stopSelling(flightId)) {
            throw new BaseException("停止售票");
        }
        String string =
                "BK" + orderForm.getUserId() +
                "F" + flightId +
                "T" + orderForm.getTicketId();
        orderForm.setId(string);
        orderForm.setPaymentAmount(ticketService.queryAmount(orderForm.getTicketId()));
        ticketService.subTicket(orderForm.getTicketId());
        orderFormService.save(orderForm);
        return true;
    }

    @Override
    public IPage<ShowOrderFormVO> getOrderForms(Page<ShowOrderFormVO> page, Long userId) {
        return orderFormDao.selectOrderFormPageVO(page, userId);
    }
}