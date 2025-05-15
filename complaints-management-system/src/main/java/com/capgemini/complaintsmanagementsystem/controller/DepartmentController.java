package com.capgemini.complaintsmanagementsystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaintsmanagementsystem.entity.Department;
import com.capgemini.complaintsmanagementsystem.service.DepartmentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Departments")
public class DepartmentController {
	 private final DepartmentService departmentService;

	    @Autowired
	    public DepartmentController(DepartmentService departmentService) {
	        this.departmentService = departmentService;
	    }
        
	    @GetMapping
	    public ResponseEntity<List<Department>> getAllDepartments() {
	        List<Department> departments = departmentService.getAllDepartments();
	        return ResponseEntity.status(HttpStatus.OK).body(departments);
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
	    	Department department = departmentService.getDepartmentById(id);
	        return ResponseEntity.status(HttpStatus.OK).body(department);
	    }

	   
	    @PostMapping
	    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department, BindingResult result) {
	    	if (result.hasErrors()) {
				throw new IllegalArgumentException("Invalid Data Found!!");
			}

	    	Department saved = departmentService.createDepartment(department);
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .location(URI.create("/api/departments/" + saved.getDepartmentId()))
	                .body(saved);
	    }

	   
	    @PutMapping("/{id}")
	    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @Valid @RequestBody Department updatedDepartment) {
	    	Department updated = departmentService.updateDepartment(id, updatedDepartment);
	        return ResponseEntity.status(HttpStatus.OK).body(updated);
	    }
	   
	    

	 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
	        departmentService.deleteDepartment(id);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }


	    
}
