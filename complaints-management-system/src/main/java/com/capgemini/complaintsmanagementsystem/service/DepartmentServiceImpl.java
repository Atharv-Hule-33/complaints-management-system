package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.entity.Department;
import com.capgemini.complaintsmanagementsystem.repository.DepartmentRepository;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	private  DepartmentRepository departmentRepository;

	
	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository=departmentRepository;

	}
	@Override
	public List<Department> getAllDepartments() {
		
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long departmentId) {
         return departmentRepository.findById(departmentId).orElseThrow(()->new RuntimeException("Department not found with ID: " + departmentId));
	}

	@Override
	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department updatedDepartment) {
		Department existing = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + departmentId));
		existing.setDepartmentName(updatedDepartment.getDepartmentName());
        existing.setDepartmentContact(updatedDepartment.getDepartmentContact());
        
        return departmentRepository.save(existing);
	
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		
	        departmentRepository.deleteById(departmentId);
		
	}

}
