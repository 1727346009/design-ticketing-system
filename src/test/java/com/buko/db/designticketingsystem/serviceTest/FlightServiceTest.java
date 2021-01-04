package com.buko.db.designticketingsystem.serviceTest;

import com.buko.db.designticketingsystem.DesignTicketingSystemApplication;
import com.buko.db.designticketingsystem.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Calendar;

@Slf4j
@SpringBootTest(classes = DesignTicketingSystemApplication.class)
public class FlightServiceTest {
    @Resource
    private FlightService flightService;

    @Test
    public void getFlights() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.DECEMBER, 21, 0, 0);
        log.info(String.valueOf(flightService.selectFlightVOByDate("广州", "北京", calendar.getTimeInMillis())));
    }

    @Test
    public void getFlight() {
        log.info(String.valueOf(flightService.selectFlightVOById(2L)));
    }

    @Test
    public void saleOver() {
        log.info(String.valueOf(flightService.stopSelling(2L)));
    }

}
