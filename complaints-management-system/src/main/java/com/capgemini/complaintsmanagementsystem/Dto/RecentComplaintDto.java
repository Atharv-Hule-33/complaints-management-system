package com.capgemini.complaintsmanagementsystem.Dto;

import java.time.LocalDateTime;

public class RecentComplaintDto {
	private Long id;
	private String department;
	private String type;
	private LocalDateTime filedOn;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getFiledOn() {
		return filedOn;
	}

	public void setFiledOn(LocalDateTime filedOn) {
		this.filedOn = filedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
