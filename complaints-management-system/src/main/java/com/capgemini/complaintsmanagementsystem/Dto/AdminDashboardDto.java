package com.capgemini.complaintsmanagementsystem.Dto;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;

import java.time.LocalDateTime;
import java.util.Map;

public class AdminDashboardDto {
    private Map<LocalDateTime,Long> complaintsCountByDate;
    private Map<ComplaintSeverity,Long> complaintsCountBySeverity;
    private Long filedComplaints;
    private Long inProgressComplaints;
    private Long resolvedComplaints;
    private Long closedComplaints;
    private Long numberOfDepartments;
    private Long numberOfUsers;
    private Long numberOfComplaints;

    public AdminDashboardDto(Map<LocalDateTime, Long> complainCountByDate, Map<ComplaintSeverity, Long> complainCountBySeverity, Long filedComplaints, Long inProgressComplaints, Long resolvedComplaints, Long closedComplaints, Long numberOfDepartments, Long numberOfUsers, Long numberOfComplaints) {
        this.complaintsCountByDate = complainCountByDate;
        this.complaintsCountBySeverity = complainCountBySeverity;
        this.filedComplaints = filedComplaints;
        this.inProgressComplaints = inProgressComplaints;
        this.resolvedComplaints = resolvedComplaints;
        this.closedComplaints = closedComplaints;
        this.numberOfDepartments = numberOfDepartments;
        this.numberOfUsers = numberOfUsers;
        this.numberOfComplaints = numberOfComplaints;
    }

    public Map<LocalDateTime, Long> getComplaintsCountByDate() {
        return complaintsCountByDate;
    }

    public void setComplaintsCountByDate(Map<LocalDateTime, Long> complaintsCountByDate) {
        this.complaintsCountByDate = complaintsCountByDate;
    }

    public Map<ComplaintSeverity, Long> getComplaintsCountBySeverity() {
        return complaintsCountBySeverity;
    }

    public void setComplaintsCountBySeverity(Map<ComplaintSeverity, Long> complaintsCountBySeverity) {
        this.complaintsCountBySeverity = complaintsCountBySeverity;
    }

    public Long getFiledComplaints() {
        return filedComplaints;
    }

    public void setFiledComplaints(Long filedComplaints) {
        this.filedComplaints = filedComplaints;
    }

    public Long getInProgressComplaints() {
        return inProgressComplaints;
    }

    public void setInProgressComplaints(Long inProgressComplaints) {
        this.inProgressComplaints = inProgressComplaints;
    }

    public Long getResolvedComplaints() {
        return resolvedComplaints;
    }

    public void setResolvedComplaints(Long resolvedComplaints) {
        this.resolvedComplaints = resolvedComplaints;
    }

    public Long getClosedComplaints() {
        return closedComplaints;
    }

    public void setClosedComplaints(Long closedComplaints) {
        this.closedComplaints = closedComplaints;
    }

    public Long getNumberOfDepartments() {
        return numberOfDepartments;
    }

    public void setNumberOfDepartments(Long numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }

    public Long getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public Long getNumberOfComplaints() {
        return numberOfComplaints;
    }

    public void setNumberOfComplaints(Long numberOfComplaints) {
        this.numberOfComplaints = numberOfComplaints;
    }
}
