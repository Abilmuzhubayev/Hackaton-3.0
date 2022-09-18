package com.asphyxia.routList.service;

import com.asphyxia.routList.converters.RouteConverter;
import com.asphyxia.routList.dao.OperatorDao;
import com.asphyxia.routList.dto.RouteCardDto;
import com.asphyxia.routList.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperatorService {

    @Autowired
    OperatorDao operatorDao;

    @Autowired
    RouteConverter routeConverter;

    @Transactional
    public List<RouteCardDto> getArrivalRoutes(Long id) {
        List<Route> routes = operatorDao.getArrivalRoutes(id);
        return routeConverter.getDtoList(routes);
    }

    @Transactional
    public List<RouteCardDto> getDepartureRoutes(Long id) {
        List<Route> routes = operatorDao.getDepartureRoutes(id);
        return routeConverter.getDtoList(routes);
    }

    @Transactional
    public void validateArrival(Long operatorId, Long routeId) {
        operatorDao.validateArrival(operatorId, routeId);
    }

    @Transactional
    public void validateDeparture(Long operatorId, Long routeId) {
        operatorDao.validateDeparture(operatorId, routeId);

    }
}
