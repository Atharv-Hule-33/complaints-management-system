package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.Dto.AdminDashboardDto;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.capgemini.complaintsmanagementsystem.repository.DepartmentRepository;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;


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
        List<AdminDashboardDto> dataList = new ArrayList<>();
        AdminDashboardDto data = new AdminDashboardDto();
        data.setNumberOfComplaints(complaintRepository.count());
        data.setNumberOfDepartments(departmentRepository.count());
        data.setNumberOfUsers(userRepository.count());
        data.setClosedComplaints(complaintRepository.countClosedComplaints());
        data.setInProgressComplaints(complaintRepository.countInProgressComplaints());
        data.setFiledComplaints(complaintRepository.countFiledComplaints());
        data.setResolvedComplaints(complaintRepository.countResolvedComplaints());
        List<Object[]> complaintsBySeverity = complaintRepository.countComplaintsBySeverity();
        Map<ComplaintSeverity,Long> complaintSeverityMap = new HashMap<>();
        List<Object[]> complaintsByDate = complaintRepository.countComplaintsByDate();
        Map<LocalDateTime,Long> complaintsByDateMap = new HashMap<>();
        for(Object[] complaint : complaintsBySeverity){
            ComplaintSeverity severity = (ComplaintSeverity) complaint[0];
            Long complaintCount = (Long) complaint[1];
            complaintSeverityMap.put(severity,complaintCount);
        }
        data.setComplaintsCountBySeverity(complaintSeverityMap);

        for(Object[] complaint : complaintsByDate){
            Object dateObject = complaint[0];
            if (dateObject instanceof java.sql.Date) {
                java.sql.Date sqlDate = (java.sql.Date) dateObject;

                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                java.time.Instant instant = utilDate.toInstant();
                java.time.LocalDateTime localDateTime = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();

                Long complaintCount = (Long) complaint[1];
                complaintsByDateMap.put(localDateTime,complaintCount);
            } else {
                // Handle the case where the date is not a java.sql.Date
                System.err.println("Unexpected date type: " + dateObject.getClass().getName());
            }
        }
        data.setComplaintsCountByDate(complaintsByDateMap);
        dataList.add(data);
        return dataList;
    }
}
