package com.capgemini.complaintsmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;

public interface ComplaintTypeRepository extends JpaRepository<ComplaintType, Long>{

	List<ComplaintType> findByComplaintSeverity(ComplaintSeverity severity);

}
