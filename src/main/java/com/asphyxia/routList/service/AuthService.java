package com.asphyxia.routList.service;

import com.asphyxia.routList.dao.UserDao;
import com.asphyxia.routList.dto.LoginResponse;
import com.asphyxia.routList.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    UserDao userDao;


    @Transactional
    public LoginResponse login(String userName, String password) {
        List<Object> resultList = userDao.getUserByLogin(userName);
        LoginResponse loginResponse = new LoginResponse();

        if (resultList != null && !resultList.isEmpty()) {
            Long userId = (Long) resultList.get(0);
            String firstName = (String) resultList.get(1);
            String lastName = (String) resultList.get(2);
            Long roleId = (Long) resultList.get(3);
            String userPassword = (String) resultList.get(4);
            if (userPassword.equals(password)) {
                loginResponse = createSuccessfulLogin(roleId, userId, firstName, lastName);
            }
        } else {
            loginResponse.setIsSuccess(false);
        }

        return loginResponse;
    }


    @Transactional
    public LoginResponse createSuccessfulLogin(Long roleId, Long userId, String firstName, String lastName) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setIsSuccess(true);
        String roleName = userDao.getRoleByRoleId(roleId);
        loginResponse.setRole(roleName);
        if ("manager".equals(roleName)) {
            loginResponse.setId(userDao.getManagerIdByUserId(userId));
        } else if ("operator".equals(roleName)) {
            loginResponse.setId(userDao.getOperatorIdByUserId(userId));
        } else if ("driver".equals(roleName)) {
            loginResponse.setId(userDao.getDriverIdByUserId(userId));
        }
        loginResponse.setName(firstName + " " + lastName);
        return loginResponse;
    }
}
