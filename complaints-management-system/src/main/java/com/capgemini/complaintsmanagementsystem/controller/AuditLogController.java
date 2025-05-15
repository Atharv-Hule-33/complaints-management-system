package com.capgemini.complaintsmanagementsystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audit-log")
public class AuditLogController {
	
	private AuditLogService auditLogService;

	@Autowired
	public AuditLogController(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}
	
	@GetMapping()
	public ResponseEntity<List<AuditLog>> getAllAuditLog() {
		return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAllAuditLog());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuditLog> getAuditLogById(@PathVariable("id") long id ) {
		return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAuditLogById(id));
	}
	
	@PostMapping()
	public ResponseEntity<AuditLog> addAuditLog(@Valid @RequestBody AuditLog auditLog, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(auditLogService.addAuditLog(auditLog));
	}
	
	@GetMapping("/complaint/{complaintId}")
    public ResponseEntity<List<AuditLog>> getLogsByComplaintId(@PathVariable("complaintId") Long complaintId) {
        return new ResponseEntity<>(auditLogService.getLogsByComplaintId(complaintId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getLogsByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(auditLogService.getLogsByUserId(userId), HttpStatus.OK);
    }

    // GET /api/audit-log/between?start=2025-05-01T00:00:00&end=2025-05-14T23:59:59

    @GetMapping("/between")
    public ResponseEntity<List<AuditLog>> getLogsBetween(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
    	    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return new ResponseEntity<>(auditLogService.getLogsBetween(start, end), HttpStatus.OK);
    }

    @GetMapping("/daily-counts")
    public ResponseEntity<List<Object[]>> getDailyLogCounts() {
        return new ResponseEntity<>(auditLogService.getDailyLogCounts(), HttpStatus.OK);
    }
	
}
