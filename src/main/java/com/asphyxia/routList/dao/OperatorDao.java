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
        String sql = "select station_id as stationId from operator where operator_id = ?";
        Long stationId = jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getLong("stationId");
            }
            return null;
        }, id);

        if (stationId != null) {
            List<Route> routes = entityManager.createNativeQuery("select * from route " +
                    "inner join plan on plan.plan_id = route.plan_id " +
                    "inner join loco_submission on plan.plan_id = loco_submission.plan_id" +
                    " where (route.destination_id = ?1 and route.in_operator_id is null)" +
                    " and loco_submission.status != ?2)", Route.class).setParameter(1, stationId).setParameter(2, Status.inFuture).getResultList();
            return routes;
        }

        return null;
    }
}
