package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.Dto.UserDashboardDto;

public interface UserDashboardService {
    UserDashboardDto getUserDashboardData(Long userId);
}
