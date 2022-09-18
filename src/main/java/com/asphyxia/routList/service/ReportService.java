//package com.asphyxia.routList.service;
//
//import com.asphyxia.routList.converters.RouteConverter;
//import com.asphyxia.routList.converters.StationDataConverter;
//import com.asphyxia.routList.converters.SubtaskConverter;
//import com.asphyxia.routList.dao.DriverDao;
//import com.asphyxia.routList.dao.ManagerDao;
//import com.asphyxia.routList.dto.LocoSubmissionDto;
//import com.asphyxia.routList.dto.PlanDto;
//import com.asphyxia.routList.dto.StationDataDto;
//import com.asphyxia.routList.dto.SubtaskDto;
//import com.asphyxia.routList.entity.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class ReportService {
//
//    @Autowired
//    DriverDao driverDao;
//    @Autowired
//    ManagerDao managerDao;
//
//    @Autowired
//    RouteConverter routeConverter;
//
//    @Autowired
//    SubtaskConverter subtaskConverter;
//
//    @Autowired
//    StationDataConverter stationDataConverter;
//
//    public byte[] generateReport(Long id) {
//        Map<String, Object> data = new HashMap<>();
//        Route route = managerDao.getRoute(id);
//        Plan plan = route.getPlan();
//        List<Subtask> subtaskList = plan.getSubtaskList();
//        LocoSubmission locoSubmission = plan.getLocoSubmission();
//        LocoAcceptance locoAcceptance = plan.getLocoAcceptance();
//        List<StationData> stationDataList = plan.getStationDataList();
//
//        data.put("locogettime", locoAcceptance.getTime());
//        data.put("locogetelectro", locoAcceptance.getElectricCounter());
//        data.put("locogetrecup", locoAcceptance.getRecuperationCounter());
//        data.put("locogetelectro", locoSubmission.getTime());
//        data.put("locogiveelectro", locoSubmission.getElectricCounter());
//        data.put("locogiverecup", locoSubmission.getRecuperationCounter());
//
//        for (Subtask subtask : subtaskList) {
//            if (subtask.getCategory().equals("arrival")) {
//                data.put("driverarrival", subtask.getTime());
//            } else {
//                data.put("driverfinish", subtask.getTime());
//            }
//        }
//
//        List<StationDataDto> stationDataDtos = stationDataConverter.getAllMessagesDTO(stationDataList);
//
//        data.put("stationdatas", stationDataDtos);
//
//
//
//    }
//
//
//
//}
