package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.buko.db.designticketingsystem.dto.FlightTicketsDTO;
import com.buko.db.designticketingsystem.po.Ticket;
import com.buko.db.designticketingsystem.service.FlightService;
import com.buko.db.designticketingsystem.service.FlightTicketService;
import com.buko.db.designticketingsystem.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author buko
 */

@Service
@Validated
@DS("master_manager")
@Transactional(rollbackFor = Exception.class)
public class FlightTicketServiceImpl implements FlightTicketService {
    @Resource
    private FlightService flightService;
    @Resource
    private TicketService ticketService;

    @Override
    public boolean addFlight(@Valid FlightTicketsDTO dto, @NotNull Long messageId) {
        dto.getFlight().setManagerId(messageId);
        flightService.addFlight(dto.getFlight());

        Long flightId = dto.getFlight().getId();

        for (Ticket t : dto.getTickets()) {
            t.setManagerId(messageId);
            t.setFlightId(flightId);
        }

        ticketService.addTickets(dto.getTickets(), flightId);

        return true;
    }
}
