package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.CodeDto;
import com.asphyxia.routList.entity.FuelConsumption;
import com.asphyxia.routList.entity.SafetyPrecaution;
import com.asphyxia.routList.entity.TechSpeed;
import org.springframework.stereotype.Component;

@Component
public class CodeConverter {
    public CodeDto getDto(FuelConsumption fuelConsumption) {
        CodeDto codeDto = new CodeDto();
        codeDto.setId(fuelConsumption.getId());
        codeDto.setDescription(fuelConsumption.getDescription());
        return codeDto;
    }

    public CodeDto getDto(TechSpeed techSpeed) {
        CodeDto codeDto = new CodeDto();
        codeDto.setId(techSpeed.getId());
        codeDto.setDescription(techSpeed.getDescription());
        return codeDto;
    }

    public CodeDto getDto(SafetyPrecaution safetyPrecaution) {
        CodeDto codeDto = new CodeDto();
        codeDto.setId(safetyPrecaution.getId());
        codeDto.setDescription(safetyPrecaution.getDescription());
        return codeDto;
    }
}
