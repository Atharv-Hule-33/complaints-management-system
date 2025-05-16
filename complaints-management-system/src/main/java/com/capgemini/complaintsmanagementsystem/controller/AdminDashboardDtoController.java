package com.capgemini.complaintsmanagementsystem.controller;

import com.capgemini.complaintsmanagementsystem.Dto.AdminDashboardDto;
import com.capgemini.complaintsmanagementsystem.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class AdminDashboardDtoController {

    AdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashboardDtoController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDashboardDto>> getData(){
        return ResponseEntity.status(HttpStatus.OK).body(adminDashboardService.getDashBoardData());
    }
}
