package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.Route;
import com.asphyxia.routList.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OperatorDao {

    @Autowired
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Route> getArrivalRoutes(Long id) {

        Long stationId = getStationIdByOperatorId(id);

        if (stationId != null) {
            List<Route> routes = entityManager.createNativeQuery("select * from route " +
                    "inner join plan on plan.route_id = route.route_id " +
                    "inner join loco_submission on plan.plan_id = loco_submission.plan_id" +
                    " where (route.destination_id = ?1 and route.in_operator_id is null)" +
                    " and loco_submission.status != ?2", Route.class).setParameter(1, stationId).setParameter(2, Status.finished).getResultList();
            return routes;
        }

        return null;
    }

    public List<Route> getDepartureRoutes(Long id) {

        Long stationId = getStationIdByOperatorId(id);

        if (stationId != null) {
            List<Route> routes = entityManager.createNativeQuery("select * from route " +
                    "inner join plan on plan.route_id = route.route_id " +
                    "inner join loco_acceptance on plan.plan_id = loco_acceptance.plan_id" +
                    " where (route.departure_id = ?1 and route.out_operator_id is null)" +
                    " and loco_acceptance.status = ?2", Route.class).setParameter(1, stationId).setParameter(2, Status.inFuture).getResultList();
            return routes;
        }

        return null;
    }

    private Long getStationIdByOperatorId(Long operatorId) {
        String sql = "select station_id as stationId from operator where operator_id = ?";
        Long stationId = jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getLong("stationId");
            }
            return null;
        }, operatorId);
        return stationId;
    }

    public void validateArrival(Long operatorId, Long routeId) {
        String sql = "update route set route.in_operator_id = ? where route.route_id = ?";
        jdbcTemplate.update(sql, operatorId, routeId);
    }

    public void validateDeparture(Long operatorId, Long routeId) {
        String sql = "update route set route.out_operator_id = ? where route.route_id = ?";
        jdbcTemplate.update(sql, operatorId, routeId);
    }
}
