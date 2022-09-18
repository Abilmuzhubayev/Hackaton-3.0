package com.asphyxia.routList.service;

import com.asphyxia.routList.converters.LocoAcceptanceConverter;
import com.asphyxia.routList.converters.LocoSubmissionConverter;
import com.asphyxia.routList.converters.SubtaskConverter;
import com.asphyxia.routList.dao.DriverDao;
import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.dto.LocoSubmissionDto;
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

   @Transactional
    public void saveLocoAcceptance(LocoAcceptanceDto locoAcceptanceDto) {
        LocoAcceptance locoAcceptance = LocoAcceptanceConverter.getEntity(locoAcceptanceDto);
        driverDao.saveLocoAcceptance(locoAcceptance);
    }

    @Transactional
    public void saveLocoSubmission(LocoSubmissionDto locoSubmissionDto) {
       LocoSubmission locoSubmission = locoSubmissionCoverter.getEntity(locoSubmissionDto);
       driverDao.saveLocoSubmission(locoSubmission);
    }

    public void saveSubtask(SubtaskDto subtaskDto, Long planId) {
       Subtask subtask = subtaskConverter.getEntity(subtaskDto);
       Plan plan = driverDao.getPlanById(planId);
       subtask.setPlan(plan);
       driverDao.saveSubtask(subtask);
    }


    public Plan getPlanById(Long id) {
       return driverDao.getPlanById(id);
    }
    @Transactional
    public List<SafetyPrecaution> getSafetyPrecautionsById(List<Long> id) {
       return driverDao.getSafetyPrecautionById(id);
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
