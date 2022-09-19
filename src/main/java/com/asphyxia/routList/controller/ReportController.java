package com.asphyxia.routList.controller;

import com.asphyxia.routList.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{id}")
    public void generateReportByRouteId(@PathVariable("id") Long routeId) {
        //надо возвращать файл а не воид, сделаю сам
        //по роут айди даш мне аргументы в генератерепорт
        try {
            reportService.generateReport(null, null, null, null);
        } catch (Exception e) {
            log.error("Exception in generateReport: ", e);
        }
    }

}
