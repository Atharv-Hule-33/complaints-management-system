package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.Department;
import com.capgemini.complaintsmanagementsystem.exception.DepartmentNotFoundException;
import com.capgemini.complaintsmanagementsystem.repository.DepartmentRepository;


@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
	private  DepartmentRepository departmentRepository;

	
	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository=departmentRepository;

	}
	@Override
	public List<Department> getAllDepartments() {
		log.debug("Fetching all the departments from the repository");

		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long departmentId) {
		log.debug("fetching department by ID:{}",departmentId);

         return departmentRepository.findById(departmentId).orElseThrow(()->{
			 log.warn("Department not found with ID:{}",departmentId);
			 return new DepartmentNotFoundException("Department not found with ID: " + departmentId);
		 });
	}

	@Override
	public Department createDepartment(Department department) {
		log.debug("Saving new department to the repository");
		return departmentRepository.save(department);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department updatedDepartment) {
		log.debug("Updating the department by ID:{}",departmentId);

		Department existing = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> {
						log.warn("Cannot update !!! Department not found with ID:{}",departmentId);

						return new DepartmentNotFoundException("Department not found with ID: " + departmentId);
					});
		existing.setDepartmentName(updatedDepartment.getDepartmentName());
        existing.setDepartmentContact(updatedDepartment.getDepartmentContact());
        
        return departmentRepository.save(existing);
	
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		Department existing = departmentRepository.findById(departmentId).orElseThrow(()->{
			log.warn("Cannot delete !!! Department not found by ID:{}",departmentId);
			return new DepartmentNotFoundException("Department not found with ID:"+departmentId);
		});
		departmentRepository.delete(existing);
	}

}
