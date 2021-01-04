package com.buko.db.designticketingsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buko.db.designticketingsystem.annotation.power.UserPower;
import com.buko.db.designticketingsystem.dto.FlightSaleDTO;
import com.buko.db.designticketingsystem.dto.RequestResult;
import com.buko.db.designticketingsystem.enumerate.StatusCodeEnum;
import com.buko.db.designticketingsystem.service.OrderFormService;
import com.buko.db.designticketingsystem.vo.ShowOrderFormVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author buko
 */
@RestController
public class OrderFormController {
    @Resource
    private OrderFormService orderFormService;

    /**
     * 新增订单
     * @param id 用户 id
     * @param dto 票型-订单数据传输对象
     * @return 新增明细
     */
    @UserPower
    @PostMapping("/order/form")
    public RequestResult<String> addOrderForm(@RequestAttribute("id") Long id,
                                              @RequestBody FlightSaleDTO dto) {
        dto.getOrderForm().setUserId(id);
        orderFormService.saleTicket(dto.getOrderForm(), dto.getFlightId());
        return new RequestResult<>();
    }

    /**
     * 查询所有订单
     * @param id 用户 id
     * @param pageSize 页大小
     * @param pageNumber 当前页
     * @return 查询明细
     */
    @UserPower
    @GetMapping("/order/forms")
    public RequestResult<IPage<ShowOrderFormVO>> getNormalOrderForm(@RequestAttribute("id") Long id,
                                                   @RequestParam("ps") Integer pageSize,
                                                   @RequestParam("pn") Integer pageNumber) {
        Page<ShowOrderFormVO> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNumber);
        return new RequestResult<>(StatusCodeEnum.SUCCESS, orderFormService.getOrderForms(page, id));
    }
}
