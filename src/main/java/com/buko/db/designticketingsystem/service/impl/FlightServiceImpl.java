package com.buko.db.designticketingsystem.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buko.db.designticketingsystem.dao.FlightDao;
import com.buko.db.designticketingsystem.exception.BaseException;
import com.buko.db.designticketingsystem.po.Flight;
import com.buko.db.designticketingsystem.service.FlightService;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import com.buko.db.designticketingsystem.validation.FlightValidGroup;
import com.buko.db.designticketingsystem.vo.ShowFlightVO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Flight对应service实现
 *
 * @author buko 2020年12月03日
 */
@Service
@Validated
@DS("master_manager")
@Transactional(rollbackFor = Exception.class)
public class FlightServiceImpl extends ServiceImpl<FlightDao, Flight> implements FlightService {
    @Lazy
    @Resource
    private FlightService flightService;
    @Resource
    private FlightDao flightDao;

    @Override
    public List<ShowFlightVO> selectFlightVOByDate(String startingCity, String destinationCity, Long date) {
        return flightDao.selectFlightsVOByDate(startingCity, destinationCity, date);
    }

    @Override
    public ShowFlightVO selectFlightVOById(Long id) {
        return flightDao.selectFlightVOById(id);
    }

    @Override
    @Validated({FlightValidGroup.Delay.class})
    public boolean delayFlight(@Valid Flight flight) {
        UpdateWrapper<Flight> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("flight_status", flight.getFlightStatus());
        updateWrapper.set("departure_time", flight.getDepartureTime());
        updateWrapper.eq("id", flight.getId());
        updateWrapper.le("departure_time", flight.getDepartureTime());
        if (!flightService.update(updateWrapper)) {
            throw new BaseException("未作任何修改");
        }
        return true;
    }

    @Override
    @Validated({CommonValidGroup.Update.class})
    public boolean updateFlight(@Valid Flight flight) {
        UpdateWrapper<Flight> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(flight.getDuration() != null, "duration", flight.getDuration());
        updateWrapper.set(flight.getFlightStatus() != null, "flight_status", flight.getFlightStatus());
        updateWrapper.eq("id", flight.getId());
        if (flight.getDuration() == null && flight.getFlightStatus() == null) {
            throw new BaseException("未作任何修改");
        }
        if (!flightService.update(updateWrapper)) {
            throw new BaseException("未作任何修改");
        }
        return true;
    }

    @Override
    public boolean stopSelling(Long id) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("1");
        queryWrapper.eq("id", id);
        queryWrapper.ge("departure_time", System.currentTimeMillis() + 60 * 60 * 1000);
        Integer count = flightDao.selectCount(queryWrapper);
        return count == 0;
    }

    @Override
    @Validated({CommonValidGroup.Insert.class})
    public boolean addFlight(@Valid Flight flight) {
        flightService.save(flight);
        return true;
    }
}