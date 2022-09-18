package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StationDataDto {
    private Long planId;
    private Long stationId;
    private String stationName;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private Double weightNetto;
    private Double weightBrutto;
    private Integer cisterns;
    private Integer axesComposition;
    private String statusName;
    private Integer orderNumber;
}
