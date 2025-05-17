package com.capgemini.complaintsmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.service.ComplaintTypeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/complaint-types")
public class ComplaintTypeController {

	private final ComplaintTypeService complaintTypeService;

	public ComplaintTypeController(ComplaintTypeService complaintTypeService) {
		this.complaintTypeService = complaintTypeService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ComplaintType>> getAllComplaintTypes() {
		log.debug("Received request to fetch all complaint types");
		return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getAllComplaintTypes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintType> getComplaintTypeById(@PathVariable Long complaintTypeId) {
		log.debug("Complaint type fetched: {}", complaintTypeId);
		return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getComplaintTypeById(complaintTypeId));
	}

	@PostMapping
	public ResponseEntity<ComplaintType> createComplaintType(@Valid @RequestBody ComplaintType complaintType,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		log.debug("Complaint type created with ID: {}", complaintType.getComplaintTypeId());
		return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.createComplaintType(complaintType));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComplaintType> updateComplaintType(@PathVariable Long complaintTypeId,
			@Valid @RequestBody ComplaintType complaintTypeDetails, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		log.debug("Complaint type uodated with ID: {}", complaintTypeDetails.getComplaintTypeId());
		return ResponseEntity.status(HttpStatus.OK)
				.body(complaintTypeService.updateComplaintType(complaintTypeId, complaintTypeDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComplaintType(@PathVariable Long complaintTypeId) {
		complaintTypeService.deleteComplaintType(complaintTypeId);
		log.debug("Deleted complaint type with ID: {}", complaintTypeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/severity/{severity}")
	public ResponseEntity<List<ComplaintType>> getComplaintTypesBySeverity(@PathVariable ComplaintSeverity severity) {
		log.debug("Getting complaint type by Severity: {}", severity);
		return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getComplaintTypesBySeverity(severity));
	}
}
