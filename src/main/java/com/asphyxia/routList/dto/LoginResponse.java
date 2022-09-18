package com.asphyxia.routList.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Boolean isSuccess;
    private String role;
    private Long id;
    private String name;
}
