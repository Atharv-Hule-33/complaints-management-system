package com.capgemini.complaintsmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.exception.AuditLogNotFoundException;
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
		return auditLogRepository.findById(id).orElseThrow(() -> new  AuditLogNotFoundException("No log with ID : " + id));
	}
	
	@Override
    public List<AuditLog> getLogsByComplaintId(Long complaintId) {
        return auditLogRepository.findByComplaintId(complaintId);
    }

    @Override
    public List<AuditLog> getLogsByUserId(Long userId) {
        return auditLogRepository.findByUserId(userId);
    }

    @Override
    public List<AuditLog> getLogsBetween(LocalDateTime start, LocalDateTime end) {
        return auditLogRepository.findLogsBetween(start, end);
    }

    @Override
    public List<Object[]> getDailyLogCounts() {
        return auditLogRepository.getDailyLogCounts();
    }
}
