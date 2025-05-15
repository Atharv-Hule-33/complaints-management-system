package com.capgemini.complaintsmanagementsystem.repository;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	public Long countByComplaintId();
}
