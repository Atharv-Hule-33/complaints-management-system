package com.capgemini.complaintsmanagementsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id", nullable = false)
	private Long logId;

	@ManyToOne
	@JoinColumn(name = "complaint_id", nullable = false)
	@NotNull(message = "Complaint is required")
	private Complaint complaint;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private User user;

	@Column(name = "action_taken", nullable = false)
	@NotBlank(message = "Actions cannot be blank")
	private String actionTaken;

	@Column(name = "audit_log_timestamp", nullable = false)
	@NotNull(message = "Timestamp is required")
	@PastOrPresent(message = "Timestamp cannot be in the future")
	private LocalDateTime auditLogTimestamp;

	public AuditLog() {
		super();
	}

	public AuditLog(Complaint complaint, User user, String actionTaken, LocalDateTime auditLogTimestamp) {
		super();
		this.complaint = complaint;
		this.user = user;
		this.actionTaken = actionTaken;
		this.auditLogTimestamp = auditLogTimestamp;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "AuditLog [logId=" + logId + ", complaint=" + complaint + ", user=" + user + ", actionTaken="
				+ actionTaken + ", auditLogTimestamp=" + auditLogTimestamp + "]";
	}

}