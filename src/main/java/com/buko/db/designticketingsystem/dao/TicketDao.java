package com.buko.db.designticketingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buko.db.designticketingsystem.po.Ticket;
import com.buko.db.designticketingsystem.vo.ShowTicketVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 表[t_ticket]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Repository
public interface TicketDao extends BaseMapper<Ticket> {
    /**
     * 查找 ticket 最低票价
     * @param flightId flightId
     * @return showTicketVO
     */
    @Select("select id, price " +
            "from v_ticket " +
            "where flight_id = #{flightId} " +
            "order by price " +
            "limit 1")
    ShowTicketVO getTheLowestTicketFare(@Param("flightId") Long flightId);

    /**
     * 查找航班对应 ticket 信息
     * @param flightId flightId
     * @return showTicketVO
     */
    @Select("select * " +
            "from v_ticket " +
            "where flight_id = #{flightId} ")
    List<ShowTicketVO> getTicketVOByFlightId(@Param("flightId") Long flightId);

    /**
     * sub surplus by id
     * @param id Id
     * @return integre
     */
    @Update("update t_ticket " +
            "set surplus = surplus - 1 " +
            "where surplus > 0 " +
            "limit 1")
    Integer subSurplusById(@Param("id") Long id);

    /**
     * query amount dao
     * @param id id
     * @return amount
     */
    @Select("select price " +
            "from v_ticket " +
            "where id = #{id} " +
            "limit 1")
    BigDecimal queryAmount(@Param("id") Long id);
}