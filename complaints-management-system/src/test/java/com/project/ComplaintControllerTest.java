package com.project;


import com.capgemini.complaintsmanagementsystem.controller.ComplaintController;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.service.ComplaintService;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ComplaintControllerTest {

    @Mock
    private ComplaintService complaintService;

    @InjectMocks
    private ComplaintController complaintController;

    private Complaint complaint1;
    private Complaint complaint2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        complaint1 = new Complaint();
        complaint1.setComplaintId(1L);
        complaint1.setComplaintDescription("Issue with product A");

        complaint2 = new Complaint();
        complaint2.setComplaintId(2L);
        complaint2.setComplaintDescription("Problem with order delivery");
    }

    @Test
    @Description("should get all complaints")
    void getAllComplaints() {
        List<Complaint> allComplaints = Arrays.asList(complaint1, complaint2);
        when(complaintService.getAllComplaint()).thenReturn(allComplaints);

        ResponseEntity<List<Complaint>> response = complaintController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allComplaints, response.getBody());
        verify(complaintService, times(1)).getAllComplaint();
    }

    @Test
    @Description("should get complaints by ID")
    void getComplaintById() {
        Long complaintId = 2L;
        when(complaintService.getComplaintById(complaintId)).thenReturn(complaint2);

        ResponseEntity<Complaint> response = complaintController.getById(complaintId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2L, response.getBody().getComplaintId());
        verify(complaintService, times(1)).getComplaintById(complaintId);
    }

    @Test
    @Description("should add complaints")
    void addComplaint() {
        when(complaintService.addComplaint(complaint1)).thenReturn(complaint1);

        ResponseEntity<Complaint> response = complaintController.addComplaint(complaint1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(complaint1, response.getBody());
        verify(complaintService, times(1)).addComplaint(complaint1);
    }

    @Test
    @Description("should update complaints")
    void updateComplaint() {
        Long complaintId = 1L;
        Complaint updatedComplaint = new Complaint();
        updatedComplaint.setComplaintId(complaintId);
        updatedComplaint.setComplaintDescription("Updated issue description");

        when(complaintService.updateComplaint(updatedComplaint, complaintId)).thenReturn(updatedComplaint);

        ResponseEntity<Complaint> response = complaintController.uodateComplaint(updatedComplaint, complaintId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedComplaint, response.getBody());
        verify(complaintService, times(1)).updateComplaint(updatedComplaint, complaintId);
    }

    @Test
    @Description("should delete complaints")
    void deleteComplaint() {
        Long complaintId = 5L;
        doNothing().when(complaintService).deleteComplaint(complaintId);
        ResponseEntity<Void> response = complaintController.deleteComplaint(complaintId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(complaintService, times(1)).deleteComplaint(complaintId);
    }
}