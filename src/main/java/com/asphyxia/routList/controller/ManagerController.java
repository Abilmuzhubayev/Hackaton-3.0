package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/user/{id}")
    public List<RouteCardDto> getRoutes(@PathVariable("id") Long managerId) {
        try {
            return managerService.getRoutes(managerId);
        } catch (Exception e) {
            log.error("Exception in getRoutes: ", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/route/{id}")
    public List<TaskDto> getRouteTasks(@PathVariable("id") Long routeId) {
        try {
            return managerService.getRouteTasks(routeId);
        } catch (Exception e) {
            log.error("Exception in getRouteTasks: ", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/route-subtask/{id}")
    public SubtaskDto getRouteSubtask(@PathVariable("id") Long subtaskId) {
        try {
            return managerService.getRouteSubtask(subtaskId);
        } catch (Exception e) {
            log.error("Exception in getRouteSubtasks: ", e);
            return null;
        }
    }

    @GetMapping("/station-data/{id}")
    public StationDataDto getStationData(@PathVariable("id") Long stationDataId) {
        try {
            return managerService.getStationData(stationDataId);
        } catch (Exception e) {
            log.error("Exception in getStationData: ", e);
            return null;
        }
    }

    @GetMapping("/loco-submission/{id}")
    public LocoSubmissionDto getLocoSubmission(@PathVariable("id") Long locoSubmissionId) {
        try {
            return managerService.getLocoSubmission(locoSubmissionId);
        } catch (Exception e) {
            log.error("Exception in getLocoSubmission: ", e);
            return null;
        }
    }

    @GetMapping("/loco-acceptance/{id}")
    public LocoAcceptanceDto getLocoAcceptance(@PathVariable("id") Long locoAcceptanceId) {
        try {
            return managerService.getLocoAcceptance(locoAcceptanceId);
        } catch (Exception e) {
            log.error("Exception in getLocoAcceptance: ", e);
            return null;
        }
    }

    @GetMapping("/route-details/{id}")
    public RouteDetailsDto getRouteDetails(@PathVariable("id") Long routeId) {
        try {
            return managerService.getRouteDetails(routeId);
        } catch (Exception e) {
            log.error("Exception in getRouteDetails: ", e);
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<OperationResult> saveRoute(@RequestBody CreateRouteDto createRouteDto) {
        OperationResult operationResult = new OperationResult();
        try {
            managerService.saveRoute(createRouteDto);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Новый маршрут и план успешно созданы.");
        } catch (Exception e) {
            log.error("Exception in saveRoute: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка при составлений маршрута.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @GetMapping("/drivers")
    public List<DriverDto> getDrivers() {
        try {
            return managerService.getDrivers();
        } catch (Exception e) {
            log.error("Exception in getDrivers: ", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/stations")
    public List<StationDto> getStations() {
        try {
            return managerService.getStations();
        } catch (Exception e) {
            log.error("Exception in getStations: ", e);
            return Collections.emptyList();
        }
    }

}
