package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.dto.LocoSubmissionDto;
import com.asphyxia.routList.dto.OperationResult;
import com.asphyxia.routList.dto.SubtaskDto;
import com.asphyxia.routList.entity.LocoSubmission;
import com.asphyxia.routList.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@Slf4j
public class DriverController {

    @Autowired
    DriverService driverService;

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
    public ResponseEntity<OperationResult> saveSubtask(@RequestBody SubtaskDto subtaskDto, @RequestParam Long planId) {
        OperationResult operationResult = new OperationResult();
        try {
            driverService.saveSubtask(subtaskDto, planId);
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


}
