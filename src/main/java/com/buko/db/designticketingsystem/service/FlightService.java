package com.buko.db.designticketingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buko.db.designticketingsystem.po.Flight;
import com.buko.db.designticketingsystem.vo.ShowFlightVO;

import javax.validation.Valid;
import java.util.List;

/**
 * Flight对应Service
 *
 * @author buko 2020年12月03日
 */
public interface FlightService extends IService<Flight> {
    /**
     * select flight page vo
     * @param startingCity 起点
     * @param destinationCity 终点
     * @param date 日期
     * @return flights
     */
    List<ShowFlightVO> selectFlightVOByDate(String startingCity, String destinationCity, Long date);

    /**
     * 获取航班详细信息
     * @param id id
     * @return show flight view
     */
    ShowFlightVO selectFlightVOById(Long id);

    /**
     * 航班延误
     * @param flight flight
     * @return boolean
     */
    boolean delayFlight(@Valid Flight flight);

    /**
     * update flight
     * @param flight flight
     * @return boolean
     */
    boolean updateFlight(@Valid Flight flight);

    boolean stopSelling(Long id);

    boolean addFlight(@Valid Flight flight);
}