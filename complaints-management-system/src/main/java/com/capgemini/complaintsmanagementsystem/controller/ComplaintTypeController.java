package com.capgemini.complaintsmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.service.ComplaintTypeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/complaint-types")
public class ComplaintTypeController {

	private final ComplaintTypeService complaintTypeService;

	public ComplaintTypeController(ComplaintTypeService complaintTypeService) {
		this.complaintTypeService = complaintTypeService;
	}

	@GetMapping
	public ResponseEntity<List<ComplaintType>> getAllComplaintTypes() {
		return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getAllComplaintTypes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintType> getComplaintTypeById(@PathVariable Long id) {
		return ResponseEntity.ok(complaintTypeService.getComplaintTypeById(id));
	}

	@PostMapping
	public ResponseEntity<ComplaintType> createComplaintType(@RequestBody ComplaintType complaintType) {
		return ResponseEntity.ok(complaintTypeService.createComplaintType(complaintType));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComplaintType> updateComplaintType(@PathVariable Long id,
			@RequestBody ComplaintType complaintTypeDetails) {
		return ResponseEntity.ok(complaintTypeService.updateComplaintType(id, complaintTypeDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComplaintType(@PathVariable Long id) {
		complaintTypeService.deleteComplaintType(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/severity/{severity}")
	public ResponseEntity<List<ComplaintType>> getComplaintTypesBySeverity(@PathVariable ComplaintSeverity severity) {
		return ResponseEntity.ok(complaintTypeService.getComplaintTypesBySeverity(severity));
	}
}
