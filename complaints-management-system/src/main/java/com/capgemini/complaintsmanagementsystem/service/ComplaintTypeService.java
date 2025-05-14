package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;

public interface ComplaintTypeService {
	List<ComplaintType> getAllComplaintTypes();

	ComplaintType getComplaintTypeById(Long id);

	ComplaintType createComplaintType(ComplaintType complaintType);

	ComplaintType updateComplaintType(Long id, ComplaintType complaintTypeDetails);

	void deleteComplaintType(Long id);

	List<ComplaintType> getComplaintTypesBySeverity(ComplaintSeverity severity);
}
