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
	private Long logID;
	private Long complaintID;
	private Long userID;
	private String actionTaken; 
	private LocalDateTime timestamp;
	public AuditLog() {
	}
	public AuditLog(Long logID, Long complaintID, Long userID, String actionTaken, LocalDateTime timestamp) {
		this.logID = logID;
		this.complaintID = complaintID;
		this.userID = userID;
		this.actionTaken = actionTaken;
		this.timestamp = timestamp;
	}
	public Long getLogID() {
		return logID;
	}
	public void setLogID(Long logID) {
		this.logID = logID;
	}
	public Long getComplaintID() {
		return complaintID;
	}
	public void setComplaintID(Long complaintID) {
		this.complaintID = complaintID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "AuditLog [logID=" + logID + ", complaintID=" + complaintID + ", userID=" + userID + ", actionTaken="
				+ actionTaken + ", timestamp=" + timestamp + "]";
	}
	
}