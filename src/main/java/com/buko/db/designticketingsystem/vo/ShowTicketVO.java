package com.buko.db.designticketingsystem.vo;

import com.buko.db.designticketingsystem.enumerate.impl.CabinClassEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author buko
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowTicketVO implements Serializable {
    private Long id;

    private Integer price;

    private BigDecimal discount;

    private Integer surplus;

    private CabinClassEnum cabinClass;

    private Long flightId;
}
