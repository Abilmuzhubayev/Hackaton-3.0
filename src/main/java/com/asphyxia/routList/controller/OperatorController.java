package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.service.DriverService;
import com.asphyxia.routList.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
