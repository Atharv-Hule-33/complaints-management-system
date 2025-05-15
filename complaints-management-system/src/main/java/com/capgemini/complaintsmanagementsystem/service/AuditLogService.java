package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;

public interface AuditLogService {
	
	public List<AuditLog> getAllAuditLog();
	
	public AuditLog getAuditLogById(long id);
	
	public AuditLog addAuditLog(AuditLog log);
	
}
