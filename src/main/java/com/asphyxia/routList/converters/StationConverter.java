package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.CreateStationDataDto;
import com.asphyxia.routList.dto.StationDto;
import com.asphyxia.routList.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class StationConverter {
    public StationDto getDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setStationId(station.getId());
        stationDto.setStationName(station.getName());
        return stationDto;
    }
}
