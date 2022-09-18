package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.DriverDto;
import com.asphyxia.routList.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter {
    public DriverDto getDto(Driver driver, String driverName) {
        DriverDto driverDto = new DriverDto();
        driverDto.setDriverId(driver.getId());
        driverDto.setDriverName(driverName);
        return driverDto;
    }
}
