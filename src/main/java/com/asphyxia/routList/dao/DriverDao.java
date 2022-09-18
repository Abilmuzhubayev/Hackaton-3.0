package com.asphyxia.routList.dao;

import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DriverDao {

    @Autowired
    private EntityManager entityManager;

    public void saveLocoAcceptance(LocoAcceptance locoAcceptance) {
        entityManager.merge(locoAcceptance);
    }

    public void saveLocoSubmission(LocoSubmission locoSubmission) {
        entityManager.merge(locoSubmission);
    }

    public List<SafetyPrecaution> getSafetyPrecautionById(List<Long> precautionsId) {
        Query query = entityManager.createQuery("select s from SafetyPrecaution s where s.id in (:l)");
        query.setParameter("l", precautionsId);
        return (List<SafetyPrecaution>) query.getResultList();
    }

    public Plan getPlanById(Long id) {
        return entityManager.find(Plan.class, id);
    }

    public void saveSubtask(Subtask subtask) {
        entityManager.merge(subtask);
    }

    public List<FuelConsumption> getFuelConsumptionById(List<Long> consumptionsId) {
        Query query = entityManager.createQuery("select s from FuelConsumption s where s.id in (:l)");
        query.setParameter("l", consumptionsId);
        return (List<FuelConsumption>) query.getResultList();
    }
    public List<TechSpeed> getTechSpeeds(List<Long> speedsId) {
        Query query = entityManager.createQuery("select s from TechSpeed s where s.id in (:l)");
        query.setParameter("l", speedsId);
        return (List<TechSpeed>) query.getResultList();
    }

}
