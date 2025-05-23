package com.capgemini.complaintsmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;

public interface AuditLogService {

	public List<AuditLog> getAllAuditLog();

	public AuditLog getAuditLogById(Long logId);

	public AuditLog addAuditLog(AuditLog log);

	List<AuditLog> getLogsByComplaintId(Long complaintId);

	List<AuditLog> getLogsByUserId(Long userId);

	List<AuditLog> getLogsBetween(LocalDateTime start, LocalDateTime end);

	List<Object[]> getDailyLogCounts();

	List<AuditLog> getFilteredAuditLogs(Long complaintId, Long userId, Long departmentId, Long complaintTypeId,
			String actionTaken, LocalDateTime startDate, LocalDateTime endDate);

}
