package com.capgemini.complaintsmanagementsystem.controller;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.service.ComplaintService;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@Slf4j
public class ComplaintController {

	ComplaintService complaintService;

	@Autowired
	public ComplaintController(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Complaint>> getAll() {
		log.debug("Received request to fetch all complaints");
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplaint());

	}
	
	@GetMapping("/{complaintId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Complaint> getById(@PathVariable Long complaintId) {
		log.debug("Request received to fetch complaint by ID:{}",complaintId);
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintById(complaintId));
	}

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Complaint> addComplaint(@Valid @RequestBody Complaint complaint, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("Invalid data found while creating");
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		log.debug("Request received to create complaint:{}",complaint);
		return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.addComplaint(complaint));
	}

	@PutMapping("/{complaintId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Complaint> updateComplaint(@Valid @RequestBody Complaint complaint,
			@PathVariable Long complaintId, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("Invalid data found while updating");
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		log.debug("request received to to update the complaint:{}",complaint);
		return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.updateComplaint(complaint, complaintId));
	}

	@DeleteMapping("/{complaintId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteComplaint(@PathVariable Long complaintId) {
		complaintService.deleteComplaint(complaintId);
		log.debug("reuqest received to delete the complaint by ID:{}",complaintId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/complaints")
	@PreAuthorize("hasRole('ADMIN')")
    public List<Complaint> getComplaints(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) String month) {

        YearMonth ym = (month != null && !month.isBlank()) ? YearMonth.parse(month) : null;
        return complaintService.getFilteredComplaints(status, departmentId, typeId, ym);
    }
	
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Complaint>> getComplaintsByUser(@PathVariable Long userId) {
	    log.debug("Request received to fetch complaints for user ID: {}", userId);
	    return ResponseEntity.ok(complaintService.getComplaintsByUser(userId));
	}

}
