package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.entity.LocoSubmission;
import com.asphyxia.routList.entity.StationData;
import com.asphyxia.routList.service.DriverService;
import com.asphyxia.routList.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/driver")
@Slf4j
public class DriverController {

    @Autowired
    DriverService driverService;

    @Autowired
    ManagerService managerService;

    @PostMapping("/saveLocoAcceptance")
    public ResponseEntity<OperationResult> saveLocoAcceptance(LocoAcceptanceDto locoAcceptanceDto) {
        OperationResult operationResult = new OperationResult();
        try {
            driverService.saveLocoAcceptance(locoAcceptanceDto);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация о приёме локомотива успешно сохранена.");
        } catch (Exception e) {
            log.error("Exception in saveLocoAcceptance: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка при сохранении приёма локомотива.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("/saveLocoSubmission")
    public ResponseEntity<OperationResult> saveLocoSubmission(LocoSubmissionDto locoSubmissionDto) {
        OperationResult operationResult = new OperationResult();
        try {
            driverService.saveLocoSubmission(locoSubmissionDto);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация о сдаче локомотива успешно сохранена.");
        } catch (Exception e) {
            log.error("Exception in saveLocoAcceptance: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка при сохранении сдачи локомотива.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("/saveSubtask")
    public ResponseEntity<OperationResult> saveSubtask(@RequestBody SubtaskDto subtaskDto) {
        OperationResult operationResult = new OperationResult();
        try {
            driverService.saveSubtask(subtaskDto);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация успешно сохранена.");
        } catch (Exception e) {
            log.error("Exception in saveLocoAcceptance: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка на стороне сервера.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("/saveStationData")
    public ResponseEntity<OperationResult> saveStationData(StationDataDto saveStationDto) {
        OperationResult operationResult = new OperationResult();
        try {
            driverService.saveStationData(saveStationDto);
            operationResult.setIsSuccess(Boolean.TRUE);
            operationResult.setMessage("Информация о работе на станции успешно сохранена.");
        } catch (Exception e) {
            log.error("Exception in saveLocoAcceptance: ", e);
            operationResult.setIsSuccess(Boolean.FALSE);
            operationResult.setMessage("Возникла ошибка при сохранении информации о работе на станции.");
            return ResponseEntity.internalServerError().body(operationResult);
        }
        return ResponseEntity.ok(operationResult);
    }

    @GetMapping("/getTasks/{id}")
    public List<TaskDto> getRouteTasks(@PathVariable("id") Long driverId) {
        Long routeId = driverService.getRouteIdByDriverId(driverId);
        System.out.println(routeId);
        return managerService.getRouteTasks(routeId);
    }

    @GetMapping("/getLocoSubmission/{id}")
    public LocoSubmissionDto getLocoSubmission(@PathVariable("id") Long id) {
        return managerService.getLocoSubmission(id);
    }

    @GetMapping("/getSubtask/{id}")
    public SubtaskDto getSubtask(@PathVariable("id") Long subtaskId) {
        return managerService.getRouteSubtask(subtaskId);
    }

    @GetMapping("getLocoAcceptance/{id}")
    public LocoAcceptanceDto getLocoAcceptance(@PathVariable("id") Long id) {
        return managerService.getLocoAcceptance(id);
    }

    @GetMapping("getStationData/{id}")
    public StationDataDto getStationData(@PathVariable("id") Long id) {
        return managerService.getStationData(id);
    }




}
