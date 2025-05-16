package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.Dto.AdminDashboardDto;

import java.util.List;

public interface AdminDashboardService {
    List<AdminDashboardDto> getDashBoardData();
}
