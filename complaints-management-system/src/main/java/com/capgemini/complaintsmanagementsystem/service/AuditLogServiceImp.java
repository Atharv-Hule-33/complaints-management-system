package com.capgemini.complaintsmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.exception.AuditLogNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.AuditLogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditLogServiceImp implements AuditLogService {

	private final AuditLogRepository auditLogRepository;

	@Autowired
	public AuditLogServiceImp(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}

	@Override
	public AuditLog addAuditLog(AuditLog auditLog) {
		log.debug("Saving new Audit Log to the Repository");
		return auditLogRepository.save(auditLog);
	}

	@Override
	public List<AuditLog> getAllAuditLog() {
		log.debug("Fetching all Audit Log from the Repository");
		return auditLogRepository.findAll();
	}

	@Override
	public AuditLog getAuditLogById(Long id) {
		log.debug("Fetching Audit Log by ID : ", id);
		return auditLogRepository.findById(id).orElseThrow(() -> {
			log.warn("Audit Log not found with ID: {}", id);
			return new AuditLogNotFoundException("No log with ID {}: " + id);
		});
	}
	
	@Override
	public List<AuditLog> getLogsByComplaintId(Long complaintId) {
		log.debug("Fetching Audit Logs by complaintID : {}", complaintId);
		return auditLogRepository.findByComplaint_ComplaintId(complaintId);
	}

	@Override
	public List<AuditLog> getLogsByUserId(Long userId) {
		log.debug("Fetching Audit Logs by userID : {}", userId);
		return auditLogRepository.findByUser_UserId(userId);
	}

	@Override
	public List<AuditLog> getLogsBetween(LocalDateTime start, LocalDateTime end) {
		if (start.isAfter(end)) {
			log.warn("Start : {} must be before end : {}", start, end);
			throw new IllegalArgumentException("Start must be before end");
		}
		log.debug("Fetching Audit Logs between {} and {}", start, end);
		return auditLogRepository.findLogsBetween(start, end);
	}
	
	@Override
	public List<Object[]> getDailyLogCounts() {
		log.debug("Fetching daily Audit Logs count");
		return auditLogRepository.getDailyLogCounts();
	}
}
