package com.asphyxia.routList.controller;

import com.asphyxia.routList.dto.LoginResponse;
import com.asphyxia.routList.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class Auth {

    @Autowired
    AuthService authService;

    @RequestMapping("/")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request) {
        String userName =  request.getParameter("userName");
        String password = request.getParameter("password");
        LoginResponse loginResponse = authService.login(userName, password);
        if (loginResponse.getIsSuccess()) {
            return ResponseEntity.badRequest().body(loginResponse);
        } else {
            return ResponseEntity.ok().body(loginResponse);
        }
    }
}
