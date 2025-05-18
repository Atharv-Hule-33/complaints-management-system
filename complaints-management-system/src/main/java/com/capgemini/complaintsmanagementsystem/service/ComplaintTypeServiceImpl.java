package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
import com.capgemini.complaintsmanagementsystem.exception.ComplaintTypeNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplaintTypeServiceImpl implements ComplaintTypeService {
	private static final String COMPLAINT_TYPE_NOT_FOUND_MSG = "ComplaintType not found with id: ";

	private final ComplaintTypeRepository complaintTypeRepository;

	@Autowired
	public ComplaintTypeServiceImpl(ComplaintTypeRepository complaintTypeRepository) {
		this.complaintTypeRepository = complaintTypeRepository;
	}

	@Override
	public List<ComplaintType> getAllComplaintTypes() {
		log.debug("Fetching all complaint types from the repository");
		return complaintTypeRepository.findAll();
	}

	@Override
	public ComplaintType getComplaintTypeById(Long complaintTypeId) {
		log.debug("Fetching complaint type by ID: {}", complaintTypeId);
		Optional<ComplaintType> optionalComplaintType = complaintTypeRepository.findById(complaintTypeId);
		return optionalComplaintType.orElseThrow(() -> {
			log.warn("While getting Complaint Type not found with ID: {}", complaintTypeId);
			return new ComplaintTypeNotFoundException(COMPLAINT_TYPE_NOT_FOUND_MSG + complaintTypeId);
		});
	}

	@Override
	public ComplaintType createComplaintType(ComplaintType complaintType) {
		log.debug("Saving new complaint type to the repository");
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public ComplaintType updateComplaintType(Long complaintTypeId, ComplaintType complaintTypeDetails) {
		ComplaintType complaintType = complaintTypeRepository.findById(complaintTypeId).orElseThrow(() -> {
			log.warn("while updating Complaint Type not found with ID: {}", complaintTypeId);
			return new ComplaintTypeNotFoundException(COMPLAINT_TYPE_NOT_FOUND_MSG + complaintTypeId);
		});

		log.debug("Updating new complaint type from the repository");
		complaintType.setComplaintType(complaintTypeDetails.getComplaintType());
		complaintType.setComplaintSeverity(complaintTypeDetails.getComplaintSeverity());
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public void deleteComplaintType(Long complaintTypeId) {
		log.debug("Deleting complaint type by ID: {}", complaintTypeId);
		ComplaintType complaintType = complaintTypeRepository.findById(complaintTypeId).orElseThrow(()->{
			log.warn("while deleting Complaint Type not found with ID: {}", complaintTypeId);
			return new ComplaintTypeNotFoundException(COMPLAINT_TYPE_NOT_FOUND_MSG + complaintTypeId);
		});
		complaintTypeRepository.delete(complaintType);

	}

	@Override
	public List<ComplaintType> getComplaintTypesBySeverity(ComplaintSeverity severity) {
		log.debug("Getting complaint type by Severity: {}", severity);
		return complaintTypeRepository.findByComplaintSeverity(severity);
	}

}
