package com.capgemini.complaintsmanagementsystem.repository;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	@Query("SELECT c.status, COUNT(c) FROM Complaint c GROUP BY c.status")
	List<Object[]> countComplaintsByStatus();

}
