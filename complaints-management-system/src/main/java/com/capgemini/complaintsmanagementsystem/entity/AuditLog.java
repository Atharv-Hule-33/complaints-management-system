package com.capgemini.complaintsmanagementsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id", nullable = false)
	private Long logId;
	
	@Column(name = "complaint_id", nullable = false)
	private Long complaintId;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "action_taken", nullable = false)
	private String actionTaken;
	
	@Column(name = "audit_log_timestamp", nullable = false)
	private LocalDateTime auditLogTimestamp;

	public AuditLog() {
	}

	public AuditLog(Long complaintId, Long userId, String actionTaken, LocalDateTime auditLogTimestamp) {
		this.complaintId = complaintId;
		this.userId = userId;
		this.actionTaken = actionTaken;
		this.auditLogTimestamp = auditLogTimestamp;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public LocalDateTime getAuditLogTimestamp() {
		return auditLogTimestamp;
	}

	public void setAuditLogTimestamp(LocalDateTime auditLogTimestamp) {
		this.auditLogTimestamp = auditLogTimestamp;
	}

	@Override
	public String toString() {
		return "AuditLog [logId=" + logId + ", complaintId=" + complaintId + ", userId=" + userId + ", actionTaken="
				+ actionTaken + ", auditLogTimestamp=" + auditLogTimestamp + "]";
	}

}