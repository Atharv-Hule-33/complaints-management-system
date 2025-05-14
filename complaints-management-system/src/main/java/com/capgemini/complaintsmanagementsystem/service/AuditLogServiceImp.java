package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.repository.AuditLogRepository;

@Service
public class AuditLogServiceImp implements AuditLogService{
	
	private AuditLogRepository auditLogRepository;
	
	@Autowired
	public AuditLogServiceImp(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}
	
	@Override
	public AuditLog addAuditLog(AuditLog log) {
		return auditLogRepository.save(log);
	}

	@Override
	public List<AuditLog> getAllAuditLog() {
		return auditLogRepository.findAll();
	}
	
	@Override
	public AuditLog getAuditLogById(long id) {
		return auditLogRepository.findById(id).orElseThrow( () -> new RuntimeException());
	}
}
