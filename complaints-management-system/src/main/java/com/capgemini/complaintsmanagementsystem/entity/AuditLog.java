package com.capgemini.complaintsmanagementsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuditLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long LogID;
	private Long ComplaintID;
	private Long UserID;
	private String ActionTaken; 
	private LocalDateTime Timestamp;
	
	public AuditLog() {
	}

	public AuditLog(Long logID, Long complaintID, Long userID, String actionTaken, LocalDateTime timestamp) {
		LogID = logID;
		ComplaintID = complaintID;
		UserID = userID;
		ActionTaken = actionTaken;
		Timestamp = timestamp;
	}

	public AuditLog(Long complaintID, Long userID, String actionTaken, LocalDateTime timestamp) {
		ComplaintID = complaintID;
		UserID = userID;
		ActionTaken = actionTaken;
		Timestamp = timestamp;
	}

	public Long getLogID() {
		return LogID;
	}

	public void setLogID(Long logID) {
		LogID = logID;
	}

	public Long getComplaintID() {
		return ComplaintID;
	}

	public void setComplaintID(Long complaintID) {
		ComplaintID = complaintID;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getActionTaken() {
		return ActionTaken;
	}

	public void setActionTaken(String actionTaken) {
		ActionTaken = actionTaken;
	}

	public LocalDateTime getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		Timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "AuditLog : [LogID=" + LogID + ", ComplaintID=" + ComplaintID + ", UserID=" + UserID + ", ActionTaken="
				+ ActionTaken + ", Timestamp=" + Timestamp + "]";
	}
	
}
