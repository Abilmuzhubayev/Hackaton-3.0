package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RouteDetailsDto {
    private String driverName;
    private String departureName;
    private String destinationName;
    private Timestamp departureTime;
    private Timestamp destinationTime;
    private String stopsCount;
}
