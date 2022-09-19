package com.asphyxia.routList.service;

import com.asphyxia.routList.converters.*;
import com.asphyxia.routList.dao.DriverDao;
import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    DriverDao driverDao;

    @Autowired
    LocoSubmissionConverter locoSubmissionConverter;

    @Autowired
    SubtaskConverter subtaskConverter;

    @Autowired
    StationDataConverter stationDataConverter;

    @Autowired
    CodeConverter codeConverter;

   @Transactional
    public void saveLocoAcceptance(LocoAcceptanceDto locoAcceptanceDto) {
        LocoAcceptance locoAcceptance = LocoAcceptanceConverter.getEntity(locoAcceptanceDto);
        locoAcceptance.setPlan(driverDao.getPlanById(locoAcceptanceDto.getPlanId()));
        driverDao.saveLocoAcceptance(locoAcceptance);
    }

    @Transactional
    public void saveLocoSubmission(LocoSubmissionDto locoSubmissionDto) {
       LocoSubmission locoSubmission = locoSubmissionConverter.getEntity(locoSubmissionDto);
       locoSubmission.setSafetyPrecautions(getSafetyPrecautionsById(locoSubmissionDto.getPrecautionsId()));
       locoSubmission.setFuelConsumptions(getFuelConsumptionsById(locoSubmissionDto.getConsumptionsId()));
       locoSubmission.setTechSpeeds(getTechSpeedsById(locoSubmissionDto.getSpeedsId()));
       locoSubmission.setPlan(driverDao.getPlanById(locoSubmissionDto.getPlanId()));
       driverDao.saveLocoSubmission(locoSubmission);
    }

    @Transactional
    public void saveSubtask(SubtaskDto subtaskDto) {
       Subtask subtask = subtaskConverter.getEntity(subtaskDto);
       subtask.setPlan(driverDao.getPlanById(subtaskDto.getPlanId()));
       driverDao.saveSubtask(subtask);
    }

    @Transactional
    public void saveStationData(StationDataDto stationDataDto) {
       StationData stationData = stationDataConverter.getEntity(stationDataDto);
       Station station = driverDao.getStationById(stationDataDto.getStationId());
       Plan plan = driverDao.getPlanById(stationDataDto.getPlanId());
       stationData.setStation(station);
       stationData.setPlan(plan);
       driverDao.saveStationData(stationData);
    }

    @Transactional
    public Plan getPlanById(Long id) {
       return driverDao.getPlanById(id);
    }

    @Transactional
    public List<SafetyPrecaution> getSafetyPrecautionsById(List<Long> id) {
       return driverDao.getSafetyPrecautionById(id);
    }

    @Transactional
    public Long getRouteIdByDriverId(Long driverId) {
       return driverDao.getRouteIdByDriverId(driverId);
    }

    @Transactional
    public List<FuelConsumption> getFuelConsumptionsById(List<Long> id) {
        return driverDao.getFuelConsumptionById(id);
    }

    @Transactional
    public List<TechSpeed> getTechSpeedsById(List<Long> id) {
        return driverDao.getTechSpeeds(id);
    }

    @Transactional
    public List<CodeDto> getFuelConsumptionCodes() {
       return driverDao.getFuelConsumptions().stream().map(x -> codeConverter.getDto(x)).collect(Collectors.toList());

    }

    @Transactional
    public List<CodeDto> getTechSpeedCodes() {
        return driverDao.getTechSpeeds().stream().map(x -> codeConverter.getDto(x)).collect(Collectors.toList());
    }

    @Transactional
    public List<CodeDto> getSafetyPrecautionCodes() {
        return driverDao.getSafetyPrecautions().stream().map(x -> codeConverter.getDto(x)).collect(Collectors.toList());
    }

}
