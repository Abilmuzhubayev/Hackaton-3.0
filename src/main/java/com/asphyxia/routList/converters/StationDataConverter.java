package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.StationDataDto;
import com.asphyxia.routList.entity.StationData;
import org.springframework.stereotype.Component;

@Component
public class StationDataConverter {

    public StationData getEntity(StationDataDto stationDataDto) {
        StationData stationData = new StationData();
        stationData.setId(stationData.getId());
        stationData.setCisterns(stationDataDto.getCisterns());
        stationData.setArrivalTime(stationDataDto.getArrivalTime());
        stationData.setDepartureTime(stationDataDto.getDepartureTime());
        stationData.setArrivalTime(stationDataDto.getArrivalTime());
        return stationData;
    }

    public StationDataDto getDto(StationData stationData) {
        StationDataDto stationDataDto = new StationDataDto();
        stationDataDto.setPlanId(stationData.getPlan().getId());
        stationDataDto.setStationId(stationData.getStation().getId());
        stationDataDto.setStationName(stationData.getStation().getName());
        stationDataDto.setArrivalTime(stationData.getArrivalTime());
        stationDataDto.setDepartureTime(stationData.getDepartureTime());
        stationDataDto.setWeightNetto(stationData.getWeightNetto());
        stationDataDto.setWeightBrutto(stationData.getWeightBrutto());
        stationDataDto.setCisterns(stationData.getCisterns());
        stationDataDto.setAxesComposition(stationData.getAxesComposition());
        stationDataDto.setStatusName(stationData.getStatus());
        stationDataDto.setOrderNumber(stationData.getOrderNumber());
        return stationDataDto;
    }

}
