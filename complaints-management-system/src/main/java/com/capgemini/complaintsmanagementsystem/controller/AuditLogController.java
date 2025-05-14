package com.capgemini.complaintsmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

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
	public ResponseEntity<AuditLog> getAAuditLogById(@PathVariable("id") long id ) {
		return ResponseEntity.status(HttpStatus.OK).body(auditLogService.getAuditLogById(id));
	}
	
	@PostMapping()
	public ResponseEntity<AuditLog> addAuditLog(@RequestBody AuditLog auditLog) {
		return ResponseEntity.status(HttpStatus.CREATED).body(auditLogService.addAuditLog(auditLog));
	}
	
}
