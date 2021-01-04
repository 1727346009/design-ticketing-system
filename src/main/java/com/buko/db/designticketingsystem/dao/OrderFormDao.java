package com.buko.db.designticketingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buko.db.designticketingsystem.po.OrderForm;
import com.buko.db.designticketingsystem.vo.ShowOrderFormVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 表[t_sale]对应实体类
 *
 * @author buko 2020年12月03日
 */
@Repository
public interface OrderFormDao extends BaseMapper<OrderForm> {

    /**
     * 查询所以订单
     * @param page 分页器
     * @param userId 用户 id
     * @return 分页器
     */
    @Select("select * from v_order_form  " +
            "where user_id=#{user_id} " +
            "order by departure_time")
    IPage<ShowOrderFormVO> selectOrderFormPageVO(Page<?> page, @Param("user_id") Long userId);

    /**
     * 查询未完成订单
     * @param page 分页器
     * @param userId 用户 id
     * @return 分页器
     */
    @Select("select * from v_order_form  " +
            "where user_id = #{user_id} " +
            "and order_form_status != 3 " +
            "order by departure_time")
    IPage<ShowOrderFormVO> selectUnfinishedOrderFormPageVO(Page<?> page, @Param("user_id") Long userId);
}