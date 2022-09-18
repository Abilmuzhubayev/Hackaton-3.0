package com.asphyxia.routList.service;

import com.asphyxia.routList.dao.ManagerDao;
import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private RouteConverter routeConverter;

    @Transactional
    public List<RouteCardDto> getRoutes(Long managerId) {
        List<Route> routes = managerDao.getRoutes();
        return routes.stream().map(x -> routeConverter.getDto(x)).collect(Collectors.toList());
    }

    @Transactional
    public List<TaskDto> getRouteTasks(Long routeId) {
    }

    @Transactional
    public SubtaskDto getRouteSubtask(Long subtaskId) {
    }

    @Transactional
    public StationDataDto getStationData(Long stationDataId) {
    }

    @Transactional
    public LocoSubmissionDto getLocoSubmission(Long locoSubmissionId) {
    }

    @Transactional
    public LocoAcceptanceDto getLocoAcceptance(Long locoAcceptanceId) {
    }

    @Transactional
    public RouteDetailsDto getRouteDetails(Long routeId) {
    }

    @Transactional
    public void saveRoute(CreateRouteDto createRouteDto) {
    }

    @Transactional
    public List<DriverDto> getDrivers() {
    }

    @Transactional
    public List<StationDto> getStations() {
    }
}
