package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    public List<Object> getUserByLogin(String userName) {
        String sql = "select user_id, first_name, last_name, role, password from user where user.username = ?1";
        List<Object> resultList = entityManager.createNativeQuery(sql).getResultList();
        return resultList;
    }

    public String getRoleByRoleId(Long roleId) {
        String sql = "select name from role where id = ?1";
        String roleName = (String) entityManager.createNativeQuery(sql).setParameter(1, roleId).getSingleResult();
        return roleName;
    }

    public Long getManagerIdByUserId (Long userId){
        String sql = "select manager_id from manager inner join user on user.user_id = manager.user_id where user.user_id = ?1";
        Long managerId = (Long) entityManager.createNativeQuery(sql).setParameter(1, userId).getSingleResult();
        return managerId;
    }

    public Long getOperatorIdByUserId(Long userId){
        String sql = "select operator_id from operator inner join user on user.user_id = operator.user_id where user.user_id = ?1";
        Long operatorId = (Long) entityManager.createNativeQuery(sql).setParameter(1, userId).getSingleResult();
        return operatorId;
    }

    public Long getDriverIdByUserId(Long userId){
        String sql = "select driver_id from driver inner join user on user.user_id = driver.user_id where user.user_id = ?1";
        Long driverId = (Long) entityManager.createNativeQuery(sql).setParameter(1, userId).getSingleResult();
        return driverId;
    }

}
