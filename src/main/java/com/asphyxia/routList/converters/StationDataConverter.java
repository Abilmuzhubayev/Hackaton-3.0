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



}
