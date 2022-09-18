package com.asphyxia.routList.dao;

import com.asphyxia.routList.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    public User getUserByLogin(String userName) {

        String sql = "select u from User u where u.userName = ?1";
        User user = (User) entityManager.createQuery(sql).setParameter(1, userName).getSingleResult();
        return user;
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
        BigInteger driverId = (BigInteger) entityManager.createNativeQuery(sql).setParameter(1, userId).getSingleResult();
        return driverId.longValue();
    }

}
