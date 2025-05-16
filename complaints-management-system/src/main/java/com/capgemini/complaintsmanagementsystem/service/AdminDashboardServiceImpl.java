package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.Dto.AdminDashboardDto;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.capgemini.complaintsmanagementsystem.repository.DepartmentRepository;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService{

    ComplaintRepository complaintRepository;
    DepartmentRepository departmentRepository;
    UserRepository userRepository;

    @Autowired
    public AdminDashboardServiceImpl(ComplaintRepository complaintRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AdminDashboardDto> getDashBoardData() {
        return List.of();
    }
}
