package com.buko.db.designticketingsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buko.db.designticketingsystem.po.OrderForm;
import com.buko.db.designticketingsystem.vo.ShowOrderFormVO;

import javax.validation.Valid;

/**
 * Sale对应Service
 *
 * @author buko 2020年12月03日
 */
public interface OrderFormService extends IService<OrderForm> {
    /**
     * 售票
     * @param orderForm OrderForm
     * @param flightId flightId
     * @return boolean
     */
    boolean saleTicket(@Valid OrderForm orderForm, Long flightId);

    /**
     * 获取订单
     * @param page 分页器
     * @param userId 用户 id
     * @return 订单分页器
     */
    IPage<ShowOrderFormVO> getOrderForms(Page<ShowOrderFormVO> page, Long userId);
}