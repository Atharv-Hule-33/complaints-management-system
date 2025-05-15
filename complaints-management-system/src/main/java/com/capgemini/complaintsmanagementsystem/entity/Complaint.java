package com.capgemini.complaintsmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long complaintId;

    @NotNull(message = "Enter the user")
    @Column(name = "user_id")
    private Long userId;
    @NotNull(message = "Enter the department")
    @Column(name = "department_id")
    private Long departmentId;
    @NotNull(message = "Enter the complaint type")
    @Column(name = "complaint_type_id")
    private Long complaintTypeId;
    @NotBlank(message = "Enter the description")
    @Column(name = "complaint_description")
    private String complaintDescription;
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @Column(name = "complaint_filed_date")
    private LocalDateTime complaintFiledDate;
    @NotBlank(message = "Enter the status")
    @Column(name = "complaint_status")
    private String complaintStatus;

    public Complaint() {
    }

    public Complaint(Long complaintId, Long userId, Long departmentId, Long complaintTypeId, String complaintDescription, LocalDateTime complaintFiledDate, String complaintStatus) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.departmentId = departmentId;
        this.complaintTypeId = complaintTypeId;
        this.complaintDescription = complaintDescription;
        this.complaintFiledDate = complaintFiledDate;
        this.complaintStatus = complaintStatus;
    }


    public Complaint(Long complaintId, String complaintDescription) {
        this.complaintId = complaintId;
        this.complaintDescription = complaintDescription;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(Long complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public LocalDateTime getComplaintFiledDate() {
        return complaintFiledDate;
    }

    public void setComplaintFiledDate(LocalDateTime complaintFiledDate) {
        this.complaintFiledDate = complaintFiledDate;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    @Override
    public String toString() {
        return "Complaints{" +
                "complaintId=" + complaintId +
                ", userId=" + userId +
                ", departmentId=" + departmentId +
                ", complaintTypeId=" + complaintTypeId +
                ", complaintDescription='" + complaintDescription + '\'' +
                ", complaintFiledDate=" + complaintFiledDate +
                ", complaintStatus='" + complaintStatus + '\'' +
                '}';
    }
}
