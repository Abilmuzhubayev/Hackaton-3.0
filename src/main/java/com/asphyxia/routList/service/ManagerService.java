package com.asphyxia.routList.service;

import com.asphyxia.routList.converters.*;
import com.asphyxia.routList.dao.ManagerDao;
import com.asphyxia.routList.dto.*;
import com.asphyxia.routList.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private RouteConverter routeConverter;
    @Autowired
    private TaskConverter taskConverter;
    @Autowired
    private SubtaskConverter subtaskConverter;
    @Autowired
    private StationDataConverter stationDataConverter;
    @Autowired
    private LocoSubmissionConverter locoSubmissionConverter;
    @Autowired
    private DriverConverter driverConverter;
    @Autowired
    private StationConverter stationConverter;

    @Transactional
    public List<RouteCardDto> getRoutes(Long managerId) {
        List<Route> routes = managerDao.getRoutes(managerId);
        List<RouteCardDto> routeCardDtos = new ArrayList<>();
        for (Route route : routes) {
            RouteCardDto routeCardDto = routeConverter.getDto(route);
            routeCardDto.setDepartureTime(managerDao.getDepartureTime(route.getId()));
            routeCardDto.setDriverName(managerDao.getDriverName(route.getId()));
            routeCardDto.setDestinationStation(managerDao.getDestinationStation(route.getId()));
            routeCardDto.setDepartureStation(managerDao.getDepartureStation(route.getId()));
            routeCardDtos.add(routeCardDto);
        }
        return routeCardDtos;
    }

    @Transactional
    public List<TaskDto> getRouteTasks(Long routeId) {
        List<TaskDto> taskDtos = new ArrayList<>();

        Route route = managerDao.getRoute(routeId);
        Plan plan = route.getPlan();

        List<Subtask> subtaskList = plan.getSubtaskList();
        List<StationData> stationDataList = plan.getStationDataList();
        Collections.sort(stationDataList, Comparator.comparingInt(StationData::getOrderNumber));
        LocoAcceptance locoAcceptance = plan.getLocoAcceptance();
        LocoSubmission locoSubmission = plan.getLocoSubmission();

        // acceptance
        taskDtos.add(taskConverter.getDto(locoAcceptance));
        // all stationData
        for (StationData stationData : stationDataList) {
            taskDtos.add(taskConverter.getDto(stationData));
        }
        // submission
        taskDtos.add(taskConverter.getDto(locoSubmission));

        for (Subtask subtask : subtaskList) {
            if (subtask.getCategory().equals("arrival")) {
                taskDtos.add(0, taskConverter.getDto(subtask));
            } else {
                taskDtos.add(taskConverter.getDto(subtask));
            }
        }

        return taskDtos;
    }

    @Transactional
    public SubtaskDto getRouteSubtask(Long subtaskId) {
        Subtask subtask = managerDao.getSubtask(subtaskId);
        return subtaskConverter.getDto(subtask);
    }

    @Transactional
    public StationDataDto getStationData(Long stationDataId) {
        StationData stationData = managerDao.getStationData(stationDataId);
        return stationDataConverter.getDto(stationData);
    }

    @Transactional
    public LocoSubmissionDto getLocoSubmission(Long locoSubmissionId) {
        LocoSubmission locoSubmission = managerDao.getLocoSubmission(locoSubmissionId);
        return LocoSubmissionConverter.getDto(locoSubmission);
    }

    @Transactional
    public LocoAcceptanceDto getLocoAcceptance(Long locoAcceptanceId) {
        LocoAcceptance locoAcceptance = managerDao.getLocoAcceptance(locoAcceptanceId);
        return LocoAcceptanceConverter.getDto(locoAcceptance);
    }

    @Transactional
    public RouteDetailsDto getRouteDetails(Long routeId) {
        RouteDetailsDto routeDetailsDto = new RouteDetailsDto();
        routeDetailsDto.setDriverName(managerDao.getDriverName(routeId));
        routeDetailsDto.setDepartureName(managerDao.getDepartureStation(routeId));
        routeDetailsDto.setDepartureTime(managerDao.getDepartureTime(routeId));
        routeDetailsDto.setDestinationName(managerDao.getDestinationStation(routeId));
        routeDetailsDto.setDestinationTime(managerDao.getDestinationTime(routeId));
        return routeDetailsDto;
    }

    @Transactional
    public void saveRoute(CreateRouteDto createRouteDto) {
        Manager manager = managerDao.getManagerById(createRouteDto.getManagerId());
        Driver driver = managerDao.getDriverById(createRouteDto.getDriverId());
        Status status = new Status();
        status.setStatusDescription(Status.inFuture);

        List<CreateStationDataDto> createStationDataDtoList = createRouteDto.getStationDataDtoList();
        CreateStationDataDto departureDataDto = createStationDataDtoList.get(0);
        CreateStationDataDto destinationDataDto = createStationDataDtoList.get(createStationDataDtoList.size() - 1);
        Station departureStation = managerDao.getStationById(departureDataDto.getStationId());
        Station destinationStation = managerDao.getStationById(destinationDataDto.getStationId());

        Route route = new Route();
        route.setManager(manager);
        route.setDriver(driver);
        route.setDepartureStation(departureStation);
        route.setDestinationStation(destinationStation);
        route.setStatus(status);

        // locoAcceptance
        LocoAcceptance locoAcceptance = new LocoAcceptance();
        locoAcceptance.setStatus(new Status());

        // locoSubmission
        LocoSubmission locoSubmission = new LocoSubmission();
        locoSubmission.setStatus(new Status());

        // arrival subtask
        Subtask arrivalSubtask = new Subtask();
        arrivalSubtask.setStatus(new Status());

        // finish subtask
        Subtask finishSubtask = new Subtask();
        finishSubtask.setStatus(new Status());

        // List<Subtask>
        List<Subtask> subtaskList = new ArrayList<>();
        subtaskList.add(arrivalSubtask);
        subtaskList.add(finishSubtask);

        // List<StationData>
        List<StationData> stationDataList = new ArrayList<>();
        Integer orderNumber = 1;
        for (CreateStationDataDto dto : createStationDataDtoList) {
            Station station = managerDao.getStationById(dto.getStationId());

            StationData stationData = new StationData();
            stationData.setStation(station);
            stationData.setArrivalTime(dto.getArrivalTime());
            stationData.setDepartureTime(dto.getDepartureTime());
            stationData.setStatus(new Status());
            stationData.setOrderNumber(orderNumber);
//            stationData.setWeightNetto();
//            stationData.setWeightBrutto();
//            stationData.setCisterns();
//            stationData.setAxesComposition();

            stationDataList.add(stationData);
            orderNumber++;
        }

        // plan
        Plan plan = new Plan();
        plan.setRoute(route);
        plan.setLocoAcceptance(locoAcceptance);
        plan.setLocoSubmission(locoSubmission);
        plan.setSubtaskList(subtaskList);
        plan.setStationDataList(stationDataList);

        managerDao.saveRoute(route);
    }

    @Transactional
    public List<DriverDto> getDrivers() {
        List<Driver> drivers = managerDao.getDrivers();
        List<DriverDto> driverDtoList = new ArrayList<>();
        for (Driver driver : drivers) {
            String driverName = managerDao.getDriverNameByDriverId(driver.getId());
            DriverDto driverDto = driverConverter.getDto(driver, driverName);
            driverDtoList.add(driverDto);
        }
        return driverDtoList;
    }

    @Transactional
    public List<StationDto> getStations() {
        return managerDao.getStations().stream().map(x -> stationConverter.getDto(x)).collect(Collectors.toList());
    }
}