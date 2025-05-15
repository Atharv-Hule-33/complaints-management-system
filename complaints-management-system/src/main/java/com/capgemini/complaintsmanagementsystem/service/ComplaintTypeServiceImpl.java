package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.exception.ComplaintTypeNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintTypeRepository;

@Service
public class ComplaintTypeServiceImpl implements ComplaintTypeService {

	private final ComplaintTypeRepository complaintTypeRepository;

	@Autowired
	public ComplaintTypeServiceImpl(ComplaintTypeRepository complaintTypeRepository) {
		this.complaintTypeRepository = complaintTypeRepository;
	}

	@Override
	public List<ComplaintType> getAllComplaintTypes() {
		return complaintTypeRepository.findAll();
	}

	@Override
	public ComplaintType getComplaintTypeById(Long complaintTypeId) {
		Optional<ComplaintType> optionalComplaintType = complaintTypeRepository.findById(complaintTypeId);
		return optionalComplaintType.orElseThrow(() -> new ComplaintTypeNotFoundException("ComplaintType not found with id: " + complaintTypeId));
	}

	@Override
	public ComplaintType createComplaintType(ComplaintType complaintType) {
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public ComplaintType updateComplaintType(Long complaintTypeId, ComplaintType complaintTypeDetails) {
		ComplaintType complaintType = complaintTypeRepository.findById(complaintTypeId).orElseThrow(() -> new ComplaintTypeNotFoundException("ComplaintType not found with id: " + complaintTypeId));
		complaintType.setComplaintType(complaintTypeDetails.getComplaintType());
		complaintType.setComplaintSeverity(complaintTypeDetails.getComplaintSeverity());
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public void deleteComplaintType(Long complaintTypeId) {
		ComplaintType complaintType = getComplaintTypeById(complaintTypeId);
		complaintTypeRepository.delete(complaintType);

	}

	@Override
	public List<ComplaintType> getComplaintTypesBySeverity(ComplaintSeverity severity) {
		 return complaintTypeRepository.findByComplaintSeverity(severity);
	}

}
