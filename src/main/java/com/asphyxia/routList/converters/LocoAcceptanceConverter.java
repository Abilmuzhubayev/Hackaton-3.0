package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.LocoAcceptanceDto;
import com.asphyxia.routList.entity.LocoAcceptance;
import com.asphyxia.routList.entity.Status;

public class LocoAcceptanceConverter {

    public static LocoAcceptance getEntity(LocoAcceptanceDto locoAcceptanceDto) {
        LocoAcceptance locoAcceptance = new LocoAcceptance();
        locoAcceptance.setId(locoAcceptanceDto.getId());
        locoAcceptance.setTime(locoAcceptanceDto.getTime());
        locoAcceptance.setElectricCounter(locoAcceptanceDto.getElectricCounter());
        locoAcceptance.setRecuperationCounter(locoAcceptanceDto.getRecuperationCounter());
        locoAcceptance.setStatus(Status.finished);
        return locoAcceptance;
    }

    public static LocoAcceptanceDto getDto(LocoAcceptance locoAcceptance) {
        LocoAcceptanceDto locoAcceptanceDto = new LocoAcceptanceDto();
        locoAcceptanceDto.setId(locoAcceptance.getId());
        locoAcceptanceDto.setTime(locoAcceptance.getTime());
        locoAcceptanceDto.setElectricCounter(locoAcceptance.getElectricCounter());
        locoAcceptanceDto.setRecuperationCounter(locoAcceptance.getRecuperationCounter());
        locoAcceptanceDto.setStatus(locoAcceptance.getStatus());
        locoAcceptanceDto.setPlanId(locoAcceptance.getPlan().getId());
        return locoAcceptanceDto;
    }
}
