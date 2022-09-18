package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RouteCardDto {
    private Long routeId;
    private String departureStation;
    private String destinationStation;
    private Timestamp departureTime;
    private String driverName;
}
