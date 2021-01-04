package com.buko.db.designticketingsystem.vo;

import com.buko.db.designticketingsystem.enumerate.impl.OrderFormStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author buko
 */
@Data
public class ShowOrderFormVO implements Serializable {
    private String id;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private Integer paymentAmount;

    private OrderFormStatusEnum orderFormStatusEnum;

    private String aircraftName;

    private String departureAirport;

    private String landingAirport;

    private Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureTime;
}
