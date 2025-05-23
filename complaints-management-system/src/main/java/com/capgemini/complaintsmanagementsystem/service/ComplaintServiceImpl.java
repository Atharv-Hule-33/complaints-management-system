package com.capgemini.complaintsmanagementsystem.service;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.exception.ComplaintNotfoundException;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
public class ComplaintServiceImpl implements ComplaintService{

    ComplaintRepository complaintRepository;
    UserRepository userRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository,UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Complaint> getAllComplaint() {
        log.debug("Fetching all the complaints from the repository");
        return complaintRepository.findAll();
    }

    @Override
    public Complaint getComplaintById(Long complaintId) {
        log.debug("fetching complaint by ID:{}",complaintId);
        return complaintRepository.findById(complaintId).orElseThrow(()->{
            log.warn("Complaint not found with ID:{}",complaintId);
           return new ComplaintNotfoundException("complaint not found with id:"+complaintId);
        });
    }

    @Override
    public Complaint addComplaint(Complaint complaint) {
        log.debug("Saving new complaint to the repository");
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint, Long complaintId) {
        log.debug("Updating the complaint by ID:{}",complaintId);
        Complaint existing = complaintRepository.findById(complaintId).orElseThrow(()->{
            log.warn("Cannot update !!! Complaint not found with ID:{}",complaintId);
           return new ComplaintNotfoundException("complaint not found");
        });
        existing.setComplaintDescription(complaint.getComplaintDescription());
        existing.setComplaintFiledDate(complaint.getComplaintFiledDate());
        existing.setComplaintStatus(complaint.getComplaintStatus());
        existing.setComplaintType(complaint.getComplaintType());
        existing.setDepartment(complaint.getDepartment());
        existing.setMyUser(complaint.getMyUser());
        return complaintRepository.save(existing);
    }

    @Override
    public void deleteComplaint(Long complaintId) {
        log.debug("Deleting the Complaint by ID:{}",complaintId);
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(()->{
            log.warn("Cannot delete !!! Complaint not found with ID:{}",complaintId);
            return new ComplaintNotfoundException("complaint not found");
        });
        complaintRepository.delete(complaint);
    }
    
    public List<Complaint> getFilteredComplaints(String status, Long departmentId, Long typeId, YearMonth month) {
	    LocalDateTime start = null;
	    LocalDateTime end = null;

	    if (month != null) {
	        start = month.atDay(1).atStartOfDay();
	        end = month.atEndOfMonth().atTime(23, 59, 59);
	    }

	    // Normalize status: "In-Progress" → "IN_PROGRESS"
	    if (status != null && !status.isBlank()) {
	        status = status.trim().toUpperCase().replace("-", "_");
	    }
	    System.out.println(status+ departmentId+typeId+ month);
	    return complaintRepository.findFilteredComplaints(status, departmentId, typeId, start, end);
	}
    
    @Override
    public List<Complaint> getComplaintsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
			log.warn("User not found with ID:{}", userId);
			return new ComplaintNotfoundException("User not found with id:" + userId);
		});
        return complaintRepository.findByUser(user);
    }
}
