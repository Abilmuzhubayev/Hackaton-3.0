package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.Route;
import com.asphyxia.routList.entity.StationData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDao {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<List<String>> getRouteDetails(Long routeId) {
        String sql = "select route.route_id as id, user.username as uname, st.station_name as dep, st2.station_name as des, route.departure_time as dept, route.destination_time as dest " +
                "from route " +
                "inner join station as st on st.station_id = route.departure_id " +
                "inner join station as st2 on st2.station_id = route.destination_id " +
                "inner join driver on driver.driver_id = route.driver_id " +
                "inner join user on user.user_id = driver.driver_id " +
                "where route.route_id = ?";
        List<List<String>> resultList = new ArrayList<>();
        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(((Long) resultSet.getLong("id")).toString());
                row.add(resultSet.getString("uname"));
                row.add(resultSet.getString("dep"));
                row.add(resultSet.getString("des"));
                row.add(resultSet.getTimestamp("dept") == null ? null : resultSet.getTimestamp("dept").toString());
                row.add(resultSet.getTimestamp("dest") == null ? null : resultSet.getTimestamp("dest").toString());
                resultList.add(row);
            }
            return null;
        }, routeId);


        return resultList;
    }

    public List<List<String>> getDriverWorkDetails(Long id) {
        String sql = "select sub.time as artime, usout.username as usoutname, sub2.time as fitime, usin.username as usinname from route " +
                "inner join plan on plan.route_id = route.route_id " +
                "inner join subtask as sub on sub.plan_id = plan.plan_id " +
                "inner join subtask as sub2 on sub2.plan_id = plan.plan_id " +
                "inner join operator as opout on opout.operator_id = route.out_operator_id " +
                "inner join operator as opin on opin.operator_id = route.in_operator_id " +
                "inner join user as usin on usin.user_id = opin.user_id " +
                "inner join user as usout on usout.user_id = opout.user_id " +
                "where route.route_id = ? and sub.category = ? and sub2.category = ?";

        List<List<String>> resultList = new ArrayList<>();

        jdbcTemplate.query(sql, resultSet -> {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(resultSet.getTimestamp("artime").toString());
                row.add(resultSet.getString("usoutname"));
                row.add(resultSet.getTimestamp("fitime").toString());
                row.add(resultSet.getString("usinname"));
                resultList.add(row);
            }
            return null;
        }, id, "arrival", "finish");

        return resultList;
    }

    public List<List<String>> getStationData(Long routeId) {
        Route route = entityManager.find(Route.class, routeId);
        List<StationData> stationDataList = route.getStationDataList();
        List<List<String>> resultList = new ArrayList<>();
        for (StationData stationData : stationDataList) {
            List<String> row = new ArrayList<>();
            row.add(stationData.getStation().getName());
            row.add(stationData.getArrivalTime() == null ? " " : stationData.getArrivalTime().toString());
            row.add(stationData.getDepartureTime() == null ? " " : stationData.getDepartureTime().toString());
            row.add(stationData.getWeightNetto() == null ? "" : stationData.getWeightNetto().toString());
            row.add(stationData.getWeightBrutto() == null ? "" : stationData.getWeightBrutto().toString());
            resultList.add(row);
        }
        return resultList;
    }
}
