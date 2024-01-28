package com.asphyxia.routList.dao;

import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class DriverDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    public Status getStatusById(Long id) {
        return entityManager.find(Status.class, id);
    }

    public Station getStationById(Long id) {
        return entityManager.find(Station.class, id);
    }

    public void saveStationData(StationData stationData) {
        entityManager.merge(stationData);
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

    public Long getRouteIdByDriverId(Long driverId) {
        String sql = "select route_id as route_id from route where driver_id = ? order by departure_time limit 1";
        Long routeId = jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getLong("route_id");
            }
            return null;
        }, driverId);
        return routeId;
    }

    public List<SafetyPrecaution> getSafetyPrecautions() {
        Query query = entityManager.createQuery("from SafetyPrecaution");
        List<SafetyPrecaution> codes = query.getResultList();
        return codes;
    }

    public List<TechSpeed> getTechSpeeds() {
        Query query = entityManager.createQuery("from TechSpeed");
        List<TechSpeed> codes = query.getResultList();
        return codes;
    }

    public List<FuelConsumption> getFuelConsumptions() {
        Query query = entityManager.createQuery("from FuelConsumption");
        List<FuelConsumption> codes = query.getResultList();
        return codes;
    }
}
