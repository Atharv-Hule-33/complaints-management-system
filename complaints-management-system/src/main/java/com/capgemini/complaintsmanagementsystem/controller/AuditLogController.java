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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/audit-log")
public class AuditLogController {

	private final AuditLogService auditLogService;

	@Autowired
	public AuditLogController(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	@GetMapping()
	public ResponseEntity<List<AuditLog>> getAllAuditLog() {
		log.info("Received Request to fetch All Audit Logs");
		List<AuditLog> auditLogs = auditLogService.getAllAuditLog();
		log.debug("Returning {} Audit Logs", auditLogs.size());
		return ResponseEntity.status(HttpStatus.OK).body(auditLogs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuditLog> getAuditLogById(@PathVariable("id") long id) {
		log.info("Received Request to fetch Audit Log with ID : {}", id);
		AuditLog auditLog = auditLogService.getAuditLogById(id);
		if (auditLog == null) {
			log.warn("Audit Log not found with ID: {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		log.debug("Audit Log Fetched : {}", auditLog);
		return ResponseEntity.status(HttpStatus.OK).body(auditLog);
	}

	@PostMapping
	public ResponseEntity<AuditLog> addAuditLog(@Valid @RequestBody AuditLog newAuditLog, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Invalid Data Found!!");
		}
		log.info("Received Request to Post {} ", newAuditLog);
		AuditLog savedAuditLog = auditLogService.addAuditLog(newAuditLog);
		log.debug("Audit Logs created with ID : {}", savedAuditLog.getLogId());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAuditLog);
	}

	@GetMapping("/complaint/{complaintId}")
	public ResponseEntity<List<AuditLog>> getLogsByComplaintId(@PathVariable("complaintId") Long complaintId) {
		log.info("Received Request to Get Audit Logs by complaintID : {}", complaintId);
		List<AuditLog> auditLogs = auditLogService.getLogsByComplaintId(complaintId);
		log.debug("Returning {} Audit Logs", auditLogs.size());
		return ResponseEntity.status(HttpStatus.OK).body(auditLogs);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AuditLog>> getLogsByUserId(@PathVariable("userId") Long userId) {
		log.info("Received Request to Get Audit Logs by userID : {}", userId);
		List<AuditLog> auditLogs = auditLogService.getLogsByUserId(userId);
		log.debug("Returning {} Audit Logs", auditLogs.size());
		return ResponseEntity.status(HttpStatus.OK).body(auditLogs);
	}

	// GET /api/audit-log/between?start=2025-05-01T00:00:00&end=2025-05-14T23:59:59
	@GetMapping("/between")
	public ResponseEntity<List<AuditLog>> getLogsBetween(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
		log.info("Received Request to Get Audit Logs between {} and {}", start, end);
		List<AuditLog> auditLogs = auditLogService.getLogsBetween(start, end);
		log.debug("Returning {} Audit Logs", auditLogs.size());
		return ResponseEntity.status(HttpStatus.OK).body(auditLogs);
	}

	@GetMapping("/daily-counts")
	public ResponseEntity<List<Object[]>> getDailyLogCounts() {
		log.info("Received Request to Get Audit Log count by Day");
		List<Object[]> counts = auditLogService.getDailyLogCounts();
		log.debug("Returning {} Daily Audit Log Counts", counts.size());
		return ResponseEntity.status(HttpStatus.OK).body(counts);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<AuditLog>> filterAuditLogs(@RequestParam(required = false) Long complaintId,
			@RequestParam(required = false) Long userId, @RequestParam(required = false) Long departmentId,
			@RequestParam(required = false) Long complaintTypeId, @RequestParam(required = false) String actionTaken,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

		List<AuditLog> logs = auditLogService.getFilteredAuditLogs(complaintId, userId, departmentId, complaintTypeId,
				actionTaken, startDate, endDate);

		return ResponseEntity.status(HttpStatus.OK).body(logs);
	}

}
