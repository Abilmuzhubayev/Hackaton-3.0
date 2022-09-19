package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.LocoSubmissionDto;
import com.asphyxia.routList.entity.LocoSubmission;
import com.asphyxia.routList.entity.Status;
import com.asphyxia.routList.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocoSubmissionConverter {
    public LocoSubmission getEntity(LocoSubmissionDto locoSubmissionDto) {
        LocoSubmission locoSubmission = new LocoSubmission();
        locoSubmission.setId(locoSubmissionDto.getId());
        locoSubmission.setTime(locoSubmissionDto.getTime());
        locoSubmission.setElectricCounter(locoSubmissionDto.getElectricCounter());
        locoSubmission.setRecuperationCounter(locoSubmissionDto.getRecuperationCounter());
        locoSubmission.setStatus(Status.finished);
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
        locoSubmissionDto.setStatus(locoSubmission.getStatus());
        locoSubmissionDto.setPlanId(locoSubmission.getPlan().getId());
        return locoSubmissionDto;
    }

}
