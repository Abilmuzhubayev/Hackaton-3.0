package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.StationDataDto;
import com.asphyxia.routList.dto.SubtaskDto;
import com.asphyxia.routList.entity.StationData;
import com.asphyxia.routList.entity.Status;
import com.asphyxia.routList.entity.Subtask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationDataConverter {

    public StationData getEntity(StationDataDto stationDataDto) {
        StationData stationData = new StationData();
        stationData.setId(stationDataDto.getId());
        stationData.setWeightNetto(stationDataDto.getWeightNetto());
        stationData.setWeightBrutto(stationDataDto.getWeightBrutto());
        stationData.setCisterns(stationDataDto.getCisterns());
        stationData.setAxesComposition(stationDataDto.getAxesComposition());
        stationData.setArrivalTime(stationDataDto.getArrivalTime());
        stationData.setDepartureTime(stationDataDto.getDepartureTime());
        stationData.setOrderNumber(stationDataDto.getOrderNumber());
        stationData.setStatus(Status.finished);
        return stationData;
    }

    public StationDataDto getDto(StationData stationData) {
        StationDataDto stationDataDto = new StationDataDto();
        stationDataDto.setId(stationData.getId());
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

    public List<StationDataDto> getAllMessagesDTO(List<StationData> messages) {
        return messages.stream().map(x -> getDto(x)).collect(Collectors.toList());
    }

}
