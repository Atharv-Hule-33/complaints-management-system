package com.capgemini.complaintsmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.complaintsmanagementsystem.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
