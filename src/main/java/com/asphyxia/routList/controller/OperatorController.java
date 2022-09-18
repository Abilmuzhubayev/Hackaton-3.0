package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.service.DriverService;
import com.asphyxia.routList.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/operator")
@Slf4j
public class OperatorController {

    @Autowired
    OperatorService operatorService;

    @GetMapping("/getArrivalRoutes/{id}")
    public List<RouteCardDto> getArrivalRoutes(@PathVariable("id") Long id) {
        try {
            return operatorService.getArrivalRoutes(id);
        } catch (Exception e) {
            log.error("Exception in getRoutesForOperator: ", e);
        }
        return null;
    }

    @GetMapping("/getDepartureRoutes/{id}")
    public List<RouteCardDto> getDepartureRoutes(@PathVariable("id") Long id) {
        try {
            return operatorService.getDepartureRoutes(id);
        } catch (Exception e) {
            log.error("Exception in getRoutesForOperator: ", e);
        }
        return null;
    }

    @PostMapping("/validateArrival")
    public void validateArrival(@RequestParam(name = "operatorId") Long operatorId, @RequestParam(name = "routeId") Long routeId) {
        try {
            operatorService.validateArrival(operatorId, routeId);
        } catch (Exception e) {
            log.error("Exception in validateArrival: ", e);
        }
    }

    @PostMapping("/validateDeparture")
    public void validateDeparture(@RequestParam(name = "operatorId") Long operatorId, @RequestParam(name = "routeId") Long routeId) {
        try {
            operatorService.validateDeparture(operatorId, routeId);
        } catch (Exception e) {
            log.error("Exception in validateDeparture: ", e);
        }
    }

}
