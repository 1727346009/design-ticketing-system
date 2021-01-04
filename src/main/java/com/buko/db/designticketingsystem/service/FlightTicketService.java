package com.buko.db.designticketingsystem.service;

import com.buko.db.designticketingsystem.dto.FlightTicketsDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author xjw13
 */
public interface FlightTicketService {
    /**
     * 新增航班 & 票型
     * @param dto dto
     * @param messageId message id
     * @return 新增明细
     */
    boolean addFlight(@Valid FlightTicketsDTO dto, @NotNull Long messageId);
}
