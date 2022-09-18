package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.LocoSubmissionDto;
import com.asphyxia.routList.entity.LocoSubmission;
import com.asphyxia.routList.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocoSubmissionConverter {

    @Autowired
    DriverService driverService;

    public LocoSubmission getEntity(LocoSubmissionDto locoSubmissionDto) {
        LocoSubmission locoSubmission = new LocoSubmission();
        locoSubmission.setId(locoSubmissionDto.getId());
        locoSubmission.setTime(locoSubmissionDto.getTime());
        locoSubmission.setElectricCounter(locoSubmissionDto.getElectricCounter());
        locoSubmission.setRecuperationCounter(locoSubmissionDto.getRecuperationCounter());
        locoSubmission.setSafetyPrecautions(driverService.getSafetyPrecautionsById(locoSubmissionDto.getPrecautionsId()));
        locoSubmission.setFuelConsumptions(driverService.getFuelConsumptionsById(locoSubmissionDto.getConsumptionsId()));
        locoSubmission.setTechSpeeds(driverService.getTechSpeedsById(locoSubmissionDto.getSpeedsId()));
        return locoSubmission;
    }

    public static LocoSubmissionDto getDto(LocoSubmission locoSubmission) {
        LocoSubmissionDto locoSubmissionDto = new LocoSubmissionDto();
        locoSubmissionDto.setId(locoSubmission.getId());
        locoSubmissionDto.setElectricCounter(locoSubmission.getElectricCounter());
        locoSubmissionDto.setTime(locoSubmission.getTime());
        locoSubmissionDto.setRecuperationCounter(locoSubmission.getRecuperationCounter());
        locoSubmissionDto.setPrecautionsId(locoSubmission.getSafetyIds());
        locoSubmissionDto.setSpeedsId(locoSubmission.getSpeedIds());
        locoSubmissionDto.setConsumptionsId(locoSubmission.getConsumptionIds());
        return locoSubmissionDto;
    }

}
