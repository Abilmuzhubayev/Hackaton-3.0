package com.asphyxia.routList.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRouteDto {
    private Long managerId;
    private Long driverId;
    private List<CreateStationDataDto> stationDataDtoList;
}
