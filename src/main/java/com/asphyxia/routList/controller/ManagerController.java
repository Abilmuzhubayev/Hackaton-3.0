package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.entity.Driver;
import com.asphyxia.routList.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/user/{id}")
    public List<RouteCardDto> getRoutes(@PathVariable("id") Long managerId) {
        return null;
    }

    @GetMapping("/route/{id}")
    public List<TaskDto> getRouteTasks(@PathVariable("id") Long routeId) {
        return null;
    }

    @GetMapping("/route-subtask/{id}")
    public List<SubtaskDto> getRouteSubtask(@PathVariable("id") Long subtaskId) {
        return null;
    }

    @GetMapping("/station-data/{id}")
    public List<StationDataDto> getStationData(@PathVariable("id") Long stationDataId) {
        return null;
    }

    @GetMapping("/loco-submission/{id}")
    public List<LocoSubmissionDto> getLocoSubmission(@PathVariable("id") Long locoSubmissionId) {
        return null;
    }

    @GetMapping("/loco-acceptance/{id}")
    public List<LocoAcceptanceDto> getLocoAcceptance(@PathVariable("id") Long locoAcceptanceId) {
        return null;
    }

    @GetMapping("/route-details/{id}")
    public List<RouteDetailsDto> getRouteDetails(@PathVariable("id") Long routeId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<OperationResult> postNewRoute(@RequestBody CreateRouteDto createRouteDto) {
        return null;
    }

    @GetMapping("/drivers")
    public List<DriverDto> getDrivers() {
        return null;
    }

    @GetMapping("/stations")
    public List<StationDto> getStations() {
        return null;
    }

}
