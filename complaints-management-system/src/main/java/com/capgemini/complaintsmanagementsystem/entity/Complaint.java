package com.capgemini.complaintsmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaint")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "complaint_id")
	private Long complaintId;	

	@NotNull(message = "User is required")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull(message = "Department is required")
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@NotNull(message = "Complaint type is required")
	@ManyToOne
	@JoinColumn(name = "complaint_type_id", nullable = false)
	private ComplaintType complaintType;

	@NotBlank(message = "Complaint description cannot be blank")
	@Size(max = 2000, message = "Description is too long")
	@Column(name = "complaint_description", columnDefinition = "TEXT")
	private String complaintDescription;

	@NotBlank(message = "Status is required")
	@Column(name = "complaint_status")
	private String complaintStatus;

	@PastOrPresent(message = "Created date cannot be in the future")
	@Column(name = "complaint_filed_date")
	private LocalDateTime complaintFiledDate;

	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Chat> chats = new ArrayList<>();

	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AuditLog> auditLogs = new ArrayList<>();

	public Complaint() {
		super();
	}

	public Complaint(User user, Department department, ComplaintType complaintType, String complaintDescription,
			String complaintStatus, LocalDateTime complaintFiledDate) {
		super();
		this.user = user;
		this.department = department;
		this.complaintType = complaintType;
		this.complaintDescription = complaintDescription;
		this.complaintStatus = complaintStatus;
		this.complaintFiledDate = complaintFiledDate;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public LocalDateTime getComplaintFiledDate() {
		return complaintFiledDate;
	}

	public void setComplaintFiledDate(LocalDateTime complaintFiledDate) {
		this.complaintFiledDate = complaintFiledDate;
	}

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", complaintType=" + complaintType + ", complaintDescription="
				+ complaintDescription + ", complaintStatus=" + complaintStatus + ", complaintFiledDate="
				+ complaintFiledDate + "]";
	}

}
