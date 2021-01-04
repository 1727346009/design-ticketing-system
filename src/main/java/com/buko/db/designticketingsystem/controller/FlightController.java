package com.buko.db.designticketingsystem.controller;

import com.buko.db.designticketingsystem.annotation.PassToken;
import com.buko.db.designticketingsystem.annotation.power.ManagerPower;
import com.buko.db.designticketingsystem.dto.FlightTicketsDTO;
import com.buko.db.designticketingsystem.dto.RequestResult;
import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.buko.db.designticketingsystem.po.Flight;
import com.buko.db.designticketingsystem.service.FlightService;
import com.buko.db.designticketingsystem.service.FlightTicketService;
import com.buko.db.designticketingsystem.vo.ShowFlightVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * @author buko
 * 航班系统
 */
@Slf4j
@RestController
public class FlightController {
    @Resource
    private FlightService flightService;
    @Resource
    private FlightTicketService flightTicketService;

    /**
     * 增加航班，包含三种票型
     * @param id 业务员 id
     * @param dto 航班-机票数据传输对象
     * @return 新增明细
     */
    @ManagerPower
    @PostMapping(value = "/flight", produces = "application/json;charset=UTF-8")
    public RequestResult<String> addFlight(@RequestAttribute("id") Long id,
                                           @RequestBody FlightTicketsDTO dto) {
        flightTicketService.addFlight(dto, id);
        return new RequestResult<>();
    }

    /**
     * 更新航班信息
     * @param flight 航班信息
     * @return 更新明细
     */
    @ManagerPower
    @PutMapping(value = "/flight", produces = "application/json;charset=UTF-8")
    public RequestResult<String> updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
        return new RequestResult<>();
    }

    /**
     * 航班延迟
     * @param flight 航班信息
     * @return 更新明细
     */
    @ManagerPower
    @PutMapping(value = "/flight/delay", produces = "application/json;charset=UTF-8")
    public RequestResult<String> delayFlight(@RequestBody Flight flight) {
        flightService.delayFlight(flight);
        return new RequestResult<>();
    }

    /**
     * 查询航班信息列表
     * @param startingPoint 出发点
     * @param destination 目的地
     * @param year 起飞时间-年
     * @param month 起飞时间-月
     * @param day 起飞时间-日
     * @return 查询明细
     */
    @PassToken
    @SuppressWarnings("MagicConstant")
    @GetMapping(value = "/flights")
    public RequestResult<List<ShowFlightVO>> getFlights(@RequestParam("startingPoint") String startingPoint,
                                                        @RequestParam("destination") String destination,
                                                        @RequestParam("year") Integer year,
                                                        @RequestParam("month") Integer month,
                                                        @RequestParam("day") Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0);
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                flightService.selectFlightVOByDate(startingPoint, destination, calendar.getTimeInMillis()));
    }

    /**
     * 查询航班详细信息
     * @param id 航班编号
     * @return 查询明细
     */
    @PassToken
    @GetMapping(value = "/flight/{id}")
    public RequestResult<ShowFlightVO> getFlight(@PathVariable Long id) {
        return new RequestResult<>(StatusCodeEnum.SUCCESS, flightService.selectFlightVOById(id));
    }
}
