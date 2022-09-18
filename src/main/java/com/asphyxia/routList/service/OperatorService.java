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
}
