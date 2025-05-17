package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;

import java.time.YearMonth;
import java.util.List;

public interface ComplaintService {
    List<Complaint> getAllComplaint();
    Complaint getComplaintById(Long complaintId);
    Complaint addComplaint(Complaint complaint);
    Complaint updateComplaint(Complaint complaint, Long complaintId);
    void deleteComplaint(Long complaintId);
    public List<Complaint> getFilteredComplaints(String status, Long departmentId, Long typeId, YearMonth month);
}
