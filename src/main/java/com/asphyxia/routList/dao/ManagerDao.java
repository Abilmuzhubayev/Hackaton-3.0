package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ManagerDao {

    @Autowired
    private EntityManager entityManager;

//    public List<Route> getRoutes(Long id) {
//        String sql = "select route from Route route, Manager manager where manager.id = ?1";
//        return entityManager.createQuery(sql).setParameter(1, id).getResultList();
//    }

    public Route getRoute(Long routeId) {
        return entityManager.find(Route.class, routeId);
    }

//    public Timestamp getDepartureTime(Long routeId) {
//        String sql = "select station_data.departure_time from station_data inner join plan on plan.plan_id = station_data.plan_id inner join route on plan.plan_id = route.plan_id where route.route_id = ?1 order by station_data.order_number";
//        Timestamp timestamp = (Timestamp) entityManager.createNativeQuery(sql).setParameter(1, routeId).setMaxResults(1).getSingleResult();
//        return timestamp;
//    }

//    public Timestamp getDestinationTime(Long routeId) {
//        String sql = "select station_data.arrival_time from station_data inner join plan on plan.plan_id = station_data.plan_id inner join route on plan.plan_id = route.plan_id where route.route_id = ?1 order by station_data.order_number descending";
//        Timestamp timestamp = (Timestamp) entityManager.createNativeQuery(sql).setParameter(1, routeId).setMaxResults(1).getSingleResult();
//        return timestamp;
//    }

//    public String getDepartureStation(Long routeId) {
//        String sql = "select station.station_name from station " +
//                "inner join station.station_id = route.departure_id where route.route_id =?1";
//        String departureStation = (String) entityManager.createNativeQuery(sql).setParameter(1, routeId).getSingleResult();
//        return departureStation;
//    }

//    public String getDestinationStation(Long routeId) {
//        String sql = "select station.station_name from station " +
//                "inner join station.station_id = route.destination_id where route.route_id =?1";
//        String departureStation = (String) entityManager.createNativeQuery(sql).setParameter(1, routeId).getSingleResult();
//        return departureStation;
//    }


//    public String getDriverName(Long routeId) {
//        String sql = "select user.first_name as name, user.last_name as surname from user inner join driver on driver.user_id = user.user_id inner join route on route.driver_id = driver.driver_id where route.route_id = ?1";
//        List<Object> resultList = entityManager.createNativeQuery(sql).setParameter(1, routeId).getResultList();
//        String name = (String) resultList.get(0);
//        String surname = (String) resultList.get(1);
//        return name + " " + surname;
//    }

    public Subtask getSubtask(Long id) {
        return entityManager.find(Subtask.class, id);
    }

    public StationData getStationData(Long id) {
        return entityManager.find(StationData.class, id);
    }

    public LocoSubmission getLocoSubmission(Long id) {
        return entityManager.find(LocoSubmission.class, id);
    }

    public LocoAcceptance getLocoAcceptance(Long id) {
        return entityManager.find(LocoAcceptance.class, id);
    }

//    public String getDriverNameByDriverId(Long driverId) {
//        String sql = "select user.first_name as name, user.last_name as surname from user inner join driver on driver.user_id = user.user_id where driver.driver_id = ?1";
//        List<Object> resultList = entityManager.createNativeQuery(sql).setParameter(1, driverId).getResultList();
//        String name = (String) resultList.get(0);
//        String surname = (String) resultList.get(1);
//        return name + " " + surname;
//    }

    public List<Driver> getDrivers() {
        Query query = entityManager.createQuery(" from Driver");
        List<Driver> drivers = query.getResultList();
        return drivers;
    }

    public List<Station> getStations() {
        Query query = entityManager.createQuery(" from Station");
        List<Station> stations = query.getResultList();
        return stations;
    }

    public void saveRoute(Route route) {
        entityManager.merge(route);
    }

    public Manager getManagerById(Long id) {
        return entityManager.find(Manager.class, id);
    }

    public Driver getDriverById(Long id) {
        return entityManager.find(Driver.class, id);
    }

    public Station getStationById(Long id) {
        return entityManager.find(Station.class, id);
    }

}
