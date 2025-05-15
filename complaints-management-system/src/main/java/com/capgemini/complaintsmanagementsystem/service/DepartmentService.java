package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;

import com.capgemini.complaintsmanagementsystem.entity.Department;



public interface DepartmentService {
	List<Department> getAllDepartments();

	Department getDepartmentById(Long departmentId);

	Department createDepartment(Department department);

	Department updateDepartment(Long departmentId, Department department);

    void deleteDepartment(Long departmentId);
    
  

}
