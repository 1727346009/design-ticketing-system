package com.buko.db.designticketingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buko.db.designticketingsystem.po.Flight;
import com.buko.db.designticketingsystem.vo.ShowFlightVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 表[t_flight]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Repository
public interface FlightDao extends BaseMapper<Flight> {
    /**
     * 通过获取 flight
     *
     * @param id id
     * @return flight
     */
    @Select("select * from v_flight where id = #{id}")
    Flight getFlightById(@Param("id") Long id);

    /**
     * select flight page vo
     * @param startingCity 起飞城市
     * @param destinationCity 目的城市
     * @param date 日期
     * @return flights
     */
    @Select("select id, aircraft_name, departure_time, duration, departure_airport, landing_airport " +
            "from v_flight " +
            "where starting_city = #{startingCity} " +
            "and destination_city = #{destinationCity} " +
            "and flight_status = 0 " +
            "and departure_specific_time - #{date} <= 24 * 3600 * 1000 " +
            "order by departure_specific_time")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "tickets",
                    one = @One(select = "com.buko.db.designticketingsystem.dao.TicketDao.getTheLowestTicketFare"))
    })
    List<ShowFlightVO> selectFlightsVOByDate(@Param("startingCity") String startingCity,
                                             @Param("destinationCity") String destinationCity,
                                             @Param("date") Long date);

    /**
     * 查看 flight 详细信息
     * @param id id
     * @return show flight vo
     */
    @Select("select id, aircraft_name, duration, " +
            "FROM_UNIXTIME(departure_time/1000) as departure_time " +
            "from v_flight " +
            "where id = #{id} " +
            "limit 1")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "tickets",
                    many = @Many(select = "com.buko.db.designticketingsystem.dao.TicketDao.getTicketVOByFlightId"))
    })
    ShowFlightVO selectFlightVOById(@Param("id") Long id);

    //IPage<Sale> getOrderForms(Page<?> page,  @Param("id") Long id, )
}