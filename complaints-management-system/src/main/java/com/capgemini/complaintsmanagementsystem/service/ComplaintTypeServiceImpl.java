package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.ComplaintSeverity;
import com.capgemini.complaintsmanagementsystem.entity.ComplaintType;
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
	public ComplaintType getComplaintTypeById(Long id) {
		Optional<ComplaintType> optionalComplaintType = complaintTypeRepository.findById(id);
		return optionalComplaintType.orElseThrow(() -> new RuntimeException("ComplaintType not found with id: " + id));
	}

	@Override
	public ComplaintType createComplaintType(ComplaintType complaintType) {
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public ComplaintType updateComplaintType(Long id, ComplaintType complaintTypeDetails) {
		ComplaintType complaintType = getComplaintTypeById(id);
		complaintType.setComplaintType(complaintTypeDetails.getComplaintType());
		complaintType.setComplaintSeverity(complaintTypeDetails.getComplaintSeverity());
		return complaintTypeRepository.save(complaintType);
	}

	@Override
	public void deleteComplaintType(Long id) {
		ComplaintType complaintType = getComplaintTypeById(id);
		complaintTypeRepository.delete(complaintType);

	}

	@Override
	public List<ComplaintType> getComplaintTypesBySeverity(ComplaintSeverity severity) {
		 return complaintTypeRepository.findByComplaintSeverity(severity);
	}

}
