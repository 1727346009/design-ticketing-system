package com.buko.db.designticketingsystem.dto;


import com.buko.db.designticketingsystem.po.Flight;
import com.buko.db.designticketingsystem.po.Ticket;
import com.buko.db.designticketingsystem.validation.CommonValidGroup;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author buko
 */
@Data
@ApiModel("航班-票务数据传输对象")
public class FlightTicketsDTO {

    @Valid
    private Flight flight;

    @Valid
    @Size(min = 3, max = 3, message = "票型数量有误", groups = {CommonValidGroup.Insert.class})
    private List<Ticket> tickets;
}
