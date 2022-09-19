package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.OperationResult;
import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.service.DriverService;
import com.asphyxia.routList.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
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
    public ResponseEntity<OperationResult> validateArrival(@RequestParam(name = "operatorId") Long operatorId, @RequestParam(name = "routeId") Long routeId) {
        OperationResult operationResult = new OperationResult();
        try {
            operatorService.validateArrival(operatorId, routeId);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация о сдаче локомотива сохранена");
        } catch (Exception e) {
            log.error("Exception in validateArrival: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка на стороне сервера.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("/validateDeparture")
    public ResponseEntity<OperationResult> validateDeparture(@RequestParam(name = "operatorId") Long operatorId, @RequestParam(name = "routeId") Long routeId) {
        OperationResult operationResult = new OperationResult();
        try {
            operatorService.validateDeparture(operatorId, routeId);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация о приеме локомотива сохранена");
        } catch (Exception e) {
            log.error("Exception in validateDeparture: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка на стороне сервера.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

}
