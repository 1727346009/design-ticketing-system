package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buko.db.designticketingsystem.dao.TicketDao;
import com.buko.db.designticketingsystem.exception.BaseException;
import com.buko.db.designticketingsystem.po.Ticket;
import com.buko.db.designticketingsystem.service.TicketService;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Ticket对应service实现
 *
 * @author buko 2020年12月03日
 */
@Validated
@Service
@DS("master_manager")
@Transactional(rollbackFor = Exception.class)
public class TicketServiceImpl extends ServiceImpl<TicketDao, Ticket> implements TicketService {
    @Lazy
    @Resource
    private TicketService ticketService;
    @Resource
    private TicketDao ticketDao;

    @Override
    @Validated({CommonValidGroup.Insert.class})
    public boolean addTickets(@Valid List<Ticket> tickets, Long flightId) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.select("1");
        wrapper.eq("flight_id", flightId).last("limit 1");
        if (ticketDao.selectCount(wrapper) != 0){
            throw new BaseException("票型已存在，不允许继续增加");
        }
        ticketService.saveBatch(tickets);
        return true;
    }

    @Override
    public boolean subTicket(Long id) {
        Integer i = ticketDao.subSurplusById(id);
        if (i != 1) {
            throw new BaseException("该票型已售完");
        }
        return true;
    }

    @Override
    public BigDecimal queryAmount(Long id) {
        return ticketDao.queryAmount(id);
    }
}