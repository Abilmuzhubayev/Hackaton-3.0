package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ManagerDao {

    @Autowired
    private EntityManager entityManager;

    public List<Route> getRoutes(Long id) {
        String sql = "select * from route where manager_id = ?1";
        return entityManager.createNativeQuery(sql).setParameter(1, id).getResultList();
    }

    public Route getRoute(Long routeId) {
        return entityManager.find(Route.class, routeId);
    }




}
