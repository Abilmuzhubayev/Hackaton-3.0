//package com.asphyxia.routList.controller;
//
//import com.asphyxia.routList.service.ReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/report")
//public class Report {
//
//    @Autowired
//    ReportService reportService;
//
//    @GetMapping("/report/{id}")
//    public byte[] generateReport(@PathVariable("id") Long id) {
//        byte[] res = reportService.generateReport(id);
//    }
//}
