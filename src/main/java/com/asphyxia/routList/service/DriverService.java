package com.asphyxia.routList.service;

import com.asphyxia.routList.converters.LocoAcceptanceConverter;
import com.asphyxia.routList.converters.LocoSubmissionConverter;
import com.asphyxia.routList.converters.StationDataConverter;
import com.asphyxia.routList.converters.SubtaskConverter;
import com.asphyxia.routList.dao.DriverDao;
import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.dto.LocoSubmissionDto;
import com.asphyxia.routList.dto.StationDataDto;
import com.asphyxia.routList.dto.SubtaskDto;
import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    DriverDao driverDao;

    @Autowired
    LocoSubmissionConverter locoSubmissionCoverter;

    @Autowired
    SubtaskConverter subtaskConverter;

    @Autowired
    StationDataConverter stationDataConverter;

   @Transactional
    public void saveLocoAcceptance(LocoAcceptanceDto locoAcceptanceDto) {
        LocoAcceptance locoAcceptance = LocoAcceptanceConverter.getEntity(locoAcceptanceDto);
        driverDao.saveLocoAcceptance(locoAcceptance);
    }

    @Transactional
    public void saveLocoSubmission(LocoSubmissionDto locoSubmissionDto) {
       LocoSubmission locoSubmission = locoSubmissionCoverter.getEntity(locoSubmissionDto);
       locoSubmission.setSafetyPrecautions(getSafetyPrecautionsById(locoSubmissionDto.getPrecautionsId()));
       locoSubmission.setFuelConsumptions(getFuelConsumptionsById(locoSubmissionDto.getConsumptionsId()));
       locoSubmission.setTechSpeeds(getTechSpeedsById(locoSubmissionDto.getSpeedsId()));
       driverDao.saveLocoSubmission(locoSubmission);
    }

    @Transactional
    public void saveSubtask(SubtaskDto subtaskDto) {
       Subtask subtask = subtaskConverter.getEntity(subtaskDto);
       driverDao.saveSubtask(subtask);
    }

    @Transactional
    public void saveStationData(StationDataDto stationDataDto) {
       StationData stationData = stationDataConverter.getEntity(stationDataDto);
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
}
