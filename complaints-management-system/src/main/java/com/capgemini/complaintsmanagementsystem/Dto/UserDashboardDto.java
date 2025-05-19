package com.capgemini.complaintsmanagementsystem.Dto;


import java.util.List;

public class UserDashboardDto {
	private long totalComplaints;
	private long resolvedComplaints;
	private long inProgressComplaints;
	private long filedComplaints;
	private List<RecentComplaintDto> recentComplaints;

	public long getTotalComplaints() {
		return totalComplaints;
	}

	public void setTotalComplaints(long totalComplaints) {
		this.totalComplaints = totalComplaints;
	}

	public long getResolvedComplaints() {
		return resolvedComplaints;
	}

	public void setResolvedComplaints(long resolvedComplaints) {
		this.resolvedComplaints = resolvedComplaints;
	}

	public long getInProgressComplaints() {
		return inProgressComplaints;
	}

	public void setInProgressComplaints(long inProgressComplaints) {
		this.inProgressComplaints = inProgressComplaints;
	}

	public long getFiledComplaints() {
		return filedComplaints;
	}

	public void setFiledComplaints(long filedComplaints) {
		this.filedComplaints = filedComplaints;
	}

	public List<RecentComplaintDto> getRecentComplaints() {
		return recentComplaints;
	}

	public void setRecentComplaints(List<RecentComplaintDto> recentComplaints) {
		this.recentComplaints = recentComplaints;
	}
}
