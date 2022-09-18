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
        Manager manager = managerDao.getManagerById(managerId);

        List<Route> routes = manager.getRouteList();
        List<RouteCardDto> routeCardDtos = new ArrayList<>();
        for (Route route : routes) {
            RouteCardDto routeCardDto = routeConverter.getDto(route);
            routeCardDto.setDepartureTime(route.getStartTime());
            routeCardDto.setDriverName(route.getDriver().getUser().getName());
            routeCardDto.setDestinationStation(route.getDestinationStation().getName());
            routeCardDto.setDepartureStation(route.getDepartureStation().getName());
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
        Route route = managerDao.getRoute(routeId);

        routeDetailsDto.setDriverName(route.getDriver().getUser().getName());
        routeDetailsDto.setDepartureName(route.getDepartureStation().getName());
        routeDetailsDto.setDepartureTime(route.getStartTime());
        routeDetailsDto.setDestinationName(route.getDestinationStation().getName());
        routeDetailsDto.setDestinationTime(route.getEndTime());
        return routeDetailsDto;
    }

    @Transactional
    public void saveRoute(CreateRouteDto createRouteDto) {
        Manager manager = managerDao.getManagerById(createRouteDto.getManagerId());
        Driver driver = managerDao.getDriverById(createRouteDto.getDriverId());

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
        route.setStatus(Status.inFuture);

        // locoAcceptance
        LocoAcceptance locoAcceptance = new LocoAcceptance();
        locoAcceptance.setStatus(Status.inFuture);

        // locoSubmission
        LocoSubmission locoSubmission = new LocoSubmission();
        locoSubmission.setStatus(Status.inFuture);

        // arrival subtask
        Subtask arrivalSubtask = new Subtask();
        arrivalSubtask.setStatus(Status.inFuture);

        // finish subtask
        Subtask finishSubtask = new Subtask();
        finishSubtask.setStatus(Status.inFuture);

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
            stationData.setStatus(Status.inFuture);
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

        route.setPlan(plan);
        managerDao.saveRoute(route);
    }

    @Transactional
    public List<DriverDto> getDrivers() {
        List<Driver> drivers = managerDao.getDrivers();
        List<DriverDto> driverDtoList = new ArrayList<>();
        for (Driver driver : drivers) {
            String driverName = driver.getUser().getName();
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