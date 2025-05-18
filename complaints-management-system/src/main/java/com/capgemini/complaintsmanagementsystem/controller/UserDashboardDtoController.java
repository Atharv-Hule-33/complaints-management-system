package com.capgemini.complaintsmanagementsystem.controller;

import com.capgemini.complaintsmanagementsystem.Dto.UserDashboardDto;
import com.capgemini.complaintsmanagementsystem.service.UserDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-dashboard")
public class UserDashboardDtoController {

    private final UserDashboardService userDashboardService;

    @Autowired
    public UserDashboardDtoController(UserDashboardService userDashboardService) {
        this.userDashboardService = userDashboardService;
    }

    @GetMapping("/{userId}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDashboardDto> getUserDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(userDashboardService.getUserDashboardData(userId));
    }
}
