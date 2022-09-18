package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateStationDataDto {
    private Long stationId;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
}
