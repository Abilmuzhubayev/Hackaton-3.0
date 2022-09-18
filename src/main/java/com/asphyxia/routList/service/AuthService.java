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
        User user = userDao.getUserByLogin(userName);
        LoginResponse loginResponse = new LoginResponse();

        if (user != null) {
            Long userId = user.getId();
            String firstName = user.getFirstname();
            String lastName = user.getLastname();
            Long roleId = user.getRole().getId();
            String userPassword = user.getPassword();
            if (userPassword.equals(password)) {
                loginResponse = createSuccessfulLogin(userId, firstName, lastName, user.getRole().getName());
            }
        } else {
            loginResponse.setIsSuccess(false);
        }

        return loginResponse;
    }


    @Transactional
    public LoginResponse createSuccessfulLogin(Long userId, String firstName, String lastName, String roleName) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setIsSuccess(true);
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
