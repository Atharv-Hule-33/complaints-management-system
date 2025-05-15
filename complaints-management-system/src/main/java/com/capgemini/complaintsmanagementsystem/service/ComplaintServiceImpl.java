package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.exception.ComplaintNotfoundException;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService{

    ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }


    @Override
    public List<Complaint> getAllComplaint() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint getComplaintById(Long complaintId) {
        return complaintRepository.findById(complaintId).orElseThrow(()->new ComplaintNotfoundException("complaint not found with id:"+complaintId));
    }

    @Override
    public Complaint addComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint, Long complaintId) {
        Complaint existing = complaintRepository.findById(complaintId).orElseThrow(()->new ComplaintNotfoundException("complaint not found"));
        existing.setComplaintDescription(complaint.getComplaintDescription());
        existing.setComplaintFiledDate(complaint.getComplaintFiledDate());
        existing.setComplaintStatus(complaint.getComplaintStatus());
        existing.setComplaintTypeId(complaint.getComplaintTypeId());
        existing.setDepartmentId(complaint.getDepartmentId());
        existing.setUserId(complaint.getUserId());
        return complaintRepository.save(existing);
    }

    @Override
    public void deleteComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(()->new ComplaintNotfoundException("complaint not found"));
        complaintRepository.delete(complaint);
    }
}
