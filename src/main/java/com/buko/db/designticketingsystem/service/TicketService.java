package com.buko.db.designticketingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buko.db.designticketingsystem.po.Ticket;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Ticket对应Service
 *
 * @author buko 2020年12月03日
 */
public interface TicketService extends IService<Ticket> {
    /**
     * 批量插入 tickets
     * @param tickets List<Ticket>
     * @param flightId 航班编号
     * @return boolean
     */
    boolean addTickets(@Valid List<Ticket> tickets, Long flightId);

    boolean subTicket(Long id);

    /**
     * 实时查找机票金额
     * @param id id
     * @return amount
     */
    BigDecimal queryAmount(Long id);
}