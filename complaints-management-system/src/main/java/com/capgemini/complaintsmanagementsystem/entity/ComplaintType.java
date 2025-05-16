package com.capgemini.complaintsmanagementsystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "complaint_type")
public class ComplaintType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_type_id")
    private Long complaintTypeId;

    @NotBlank(message = "Complaint type name is required")
    @Column(name = "complaint_type")
    private String complaintType;

    @NotNull(message = "Severity level is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "complaint_severity")
    private ComplaintSeverity complaintSeverity;

    

    @OneToMany(mappedBy = "complaintType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();

	public ComplaintType() {
	}

	public ComplaintType(Long complaintTypeId ,String complaintType, ComplaintSeverity complaintSeverity) {
		this.complaintTypeId=complaintTypeId;
		this.complaintType = complaintType;
		this.complaintSeverity = complaintSeverity;
	}
	
	public ComplaintType(String complaintType, ComplaintSeverity complaintSeverity) {
		this.complaintType = complaintType;
		this.complaintSeverity = complaintSeverity;
	}

	public Long getComplaintTypeId() {
		return complaintTypeId;
	}

	public void setComplaintTypeId(Long complaintTypeId) {
		this.complaintTypeId = complaintTypeId;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public ComplaintSeverity getComplaintSeverity() {
		return complaintSeverity;
	}

	public void setComplaintSeverity(ComplaintSeverity complaintSeverity) {
		this.complaintSeverity = complaintSeverity;
	}

	@Override
	public String toString() {
		return "ComplaintType{" + "complaintTypeId=" + complaintTypeId + ", complaintType='" + complaintType + '\''
				+ ", complaintSeverity=" + complaintSeverity + '}';
	}

}
