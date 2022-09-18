package com.asphyxia.routList.converters;

import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.dto.SubtaskDto;
import com.asphyxia.routList.entity.Route;
import com.asphyxia.routList.entity.Subtask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteConverter {

    public RouteCardDto getDto(Route route) {
        RouteCardDto routeCardDto = new RouteCardDto();
        routeCardDto.setRouteId(route.getId());
        routeCardDto.setDepartureTime(route.getStartTime());
        routeCardDto.setDriverName(route.getDriver().getUser().getName());
        routeCardDto.setDestinationStation(route.getDestinationStation().getName());
        routeCardDto.setDepartureStation(route.getDepartureStation().getName());
        return routeCardDto;
    }

    public List<RouteCardDto> getDtoList(List<Route> routes) {
        return routes.stream().map(this::getDto).collect(Collectors.toList());
    }
}
