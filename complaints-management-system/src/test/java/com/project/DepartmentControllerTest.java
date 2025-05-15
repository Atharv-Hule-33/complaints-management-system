package com.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.controller.DepartmentController;
import com.capgemini.complaintsmanagementsystem.entity.Department;
import com.capgemini.complaintsmanagementsystem.service.DepartmentService;

import java.util.Arrays;
import java.util.List;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private Department department1;
    private Department department2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        department1 = new Department(1L, "IT", "it@company.com");
        department2 = new Department(2L, "HR", "hr@company.com");
    }

    @Test
    public void getAllDepartments_ShouldReturnAllDepartments() {
       
        List<Department> expectedDepartments = Arrays.asList(department1, department2);
        when(departmentService.getAllDepartments()).thenReturn(expectedDepartments);

        
        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

        
        assertEquals(2, response.getBody().size());
        assertEquals("IT", response.getBody().get(0).getDepartmentName());
        assertEquals("it@company.com", response.getBody().get(0).getDepartmentContact());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void getDepartmentById_ShouldReturnDepartment() {
        
        when(departmentService.getDepartmentById(1L)).thenReturn(department1);

        
        ResponseEntity<Department> response = departmentController.getDepartment(1L);

        
        assertEquals("IT", response.getBody().getDepartmentName());
        assertEquals("it@company.com", response.getBody().getDepartmentContact());
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    public void createDepartment_ShouldReturnCreatedDepartment() {
        
        Department newDepartment = new Department(null, "Finance", "finance@company.com");
        Department savedDepartment = new Department(3L, "Finance", "finance@company.com");
        when(departmentService.createDepartment(newDepartment)).thenReturn(savedDepartment);

        
        ResponseEntity<Department> response = departmentController.createDepartment(newDepartment);

       
        assertEquals(3L, response.getBody().getDepartmentId());
        assertEquals("Finance", response.getBody().getDepartmentName());
        assertEquals("finance@company.com", response.getBody().getDepartmentContact());
        assertEquals(URI.create("/api/departments/3"), response.getHeaders().getLocation());
        verify(departmentService, times(1)).createDepartment(newDepartment);
    }

    @Test
    public void updateDepartment_ShouldReturnUpdatedDepartment() {
        
        Department updatedDetails = new Department(null, "IT Updated", "it-updated@company.com");
        Department expectedDepartment = new Department(1L, "IT Updated", "it-updated@company.com");
        when(departmentService.updateDepartment(1L, updatedDetails)).thenReturn(expectedDepartment);

        
        ResponseEntity<Department> response = departmentController.updateDepartment(1L, updatedDetails);

       
        assertEquals("IT Updated", response.getBody().getDepartmentName());
        assertEquals("it-updated@company.com", response.getBody().getDepartmentContact());
        verify(departmentService, times(1)).updateDepartment(1L, updatedDetails);
    }

    @Test
    public void deleteDepartment_ShouldReturnNoContent() {
        
        doNothing().when(departmentService).deleteDepartment(1L);

        
        ResponseEntity<Void> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       
        verify(departmentService, times(1)).deleteDepartment(1L);
    }



   
    
}
