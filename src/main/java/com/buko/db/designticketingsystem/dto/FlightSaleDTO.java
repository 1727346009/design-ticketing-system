package com.buko.db.designticketingsystem.dto;

import com.buko.db.designticketingsystem.po.OrderForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author buko
 */
@Data
@ApiModel
public class FlightSaleDTO {

    private Long flightId;

    private OrderForm orderForm;

}
