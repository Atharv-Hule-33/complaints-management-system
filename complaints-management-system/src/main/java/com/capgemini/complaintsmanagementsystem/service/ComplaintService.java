package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAllComplaint();
    Complaint getComplaintById(Long complaintId);
    Complaint addComplaint(Complaint complaint);
    Complaint updateComplaint(Complaint complaint, Long complaintId);
    void deleteComplaint(Long complaintId);
}
