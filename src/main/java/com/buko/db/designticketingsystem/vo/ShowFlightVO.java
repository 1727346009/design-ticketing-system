package com.buko.db.designticketingsystem.vo;

import com.buko.db.designticketingsystem.enumerate.impl.FlightStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author buko
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowFlightVO implements Serializable {
    private Long id;

    private String aircraftName;

    private String departureAirport;

    private String landingAirport;

    @JsonFormat(pattern = "HH:mm")
    private Date departureTime;

    private Integer Duration;

    private FlightStatusEnum flightStatus;

    private List<ShowTicketVO> tickets;
}