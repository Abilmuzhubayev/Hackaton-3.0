package com.asphyxia.routList.dto;

import lombok.Data;

@Data
public class RouteDetailsDto {
    private String driverName;
    private String departureName;
    private String destinationName;
    private String departureTime;
    private String destinationTime;
    private String stopsCount;
}
