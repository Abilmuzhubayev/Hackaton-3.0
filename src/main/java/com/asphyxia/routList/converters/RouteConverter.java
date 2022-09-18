package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.entity.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter {

    public RouteCardDto getDto(Route route) {
        RouteCardDto routeCardDto = new RouteCardDto();
        routeCardDto.setRouteId(route.getId());
        routeCardDto.setDepartureStation(route.getDepartureStation().getName());
//        routeCardDto.setDepartureTime();
        routeCardDto.setDestinationStation(route.getDestinationStation().getName());
//        routeCardDto.setDriverName();
        return routeCardDto;
    }
}
