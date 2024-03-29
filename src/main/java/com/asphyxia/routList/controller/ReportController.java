package com.asphyxia.routList.controller;

import com.asphyxia.routList.dao.ReportDao;
import com.asphyxia.routList.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/{id}")
    public void generateReportByRouteId(@PathVariable("id") Long routeId, HttpServletResponse response) {
        try {
            reportService.generateReport(reportService.getRouteDetails(routeId), reportService.getDriverWorkDetails(routeId), reportService.getStationsData(routeId), reportService.getLocoData(routeId));
            String fileLocation = "classpath:report/report.pdf";
            Resource resource = resourceLoader.getResource(fileLocation);
            reportService.addResource(resource, response);
        } catch (Exception e) {
            log.error("Exception in generateReport: ", e);
        }
    }

}
