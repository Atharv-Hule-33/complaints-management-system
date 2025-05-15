package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.controller.AuditLogController;
import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

@ExtendWith(MockitoExtension.class)
 class AuditLogControllerTest {
	
	@Mock
	private AuditLogService auditLogService;
	
	@InjectMocks
	private AuditLogController auditLogController;

    @Test
    @DisplayName("Get All the Audit Log's")
    void testGetAllAuditLog() {
        AuditLog log = new AuditLog();
        log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());

        List<AuditLog> mockList = Arrays.asList(log);
        when(auditLogService.getAllAuditLog()).thenReturn(mockList);

        ResponseEntity<List<AuditLog>> response = auditLogController.getAllAuditLog();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Created complaint", response.getBody().get(0).getActionTaken());
        assertEquals(1L, response.getBody().get(0).getComplaintId());
        assertEquals(1L, response.getBody().get(0).getUserId());
        }

    @Test
    @DisplayName("Get Audit Log by id")
    void testGetAuditLogById() {
    	AuditLog log = new AuditLog();
    	log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());
    
        when(auditLogService.getAuditLogById(1L)).thenReturn(log);
        
        ResponseEntity<AuditLog> response = auditLogController.getAuditLogById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Created complaint", response.getBody().getActionTaken());
        assertEquals(1L, response.getBody().getComplaintId());
        assertEquals(1L, response.getBody().getUserId());
    }
    
    @Test
    @DisplayName("Post Audit Log")
    void testAddAuditLog() {
    	AuditLog log = new AuditLog();
    	log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());
    
        when(auditLogService.addAuditLog(log)).thenReturn(log);
        
        ResponseEntity<AuditLog> response = auditLogController.addAuditLog(log);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created complaint", response.getBody().getActionTaken());
        assertEquals(1L, response.getBody().getComplaintId());
        assertEquals(1L, response.getBody().getUserId());
    }
    
    @Test
    void testGetLogsByComplaintId() {
        AuditLog log = new AuditLog();
        log.setComplaintId(1L);
        log.setActionTaken("Resolved complaint");
        when(auditLogService.getLogsByComplaintId(1L)).thenReturn(Arrays.asList(log));

        ResponseEntity<List<AuditLog>> response = auditLogController.getLogsByComplaintId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetLogsByUserId() {
        AuditLog log = new AuditLog();
        log.setUserId(2L);
        log.setActionTaken("Viewed history");
        when(auditLogService.getLogsByUserId(2L)).thenReturn(Arrays.asList(log));

        ResponseEntity<List<AuditLog>> response = auditLogController.getLogsByUserId(2L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Viewed history", response.getBody().get(0).getActionTaken());
    }

    @Test
    void testGetLogsBetween() {
        LocalDateTime start = LocalDateTime.now().minusDays(5);
        LocalDateTime end = LocalDateTime.now();
        AuditLog log = new AuditLog();
        log.setActionTaken("Reviewed log");

        when(auditLogService.getLogsBetween(start, end)).thenReturn(Arrays.asList(log));

        ResponseEntity<List<AuditLog>> response = auditLogController.getLogsBetween(start, end);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetDailyLogCounts() {
    	LocalDate date = LocalDate.of(2025, 1, 1);
    	
    	List<Object[]> mockCounts = new ArrayList<>();
	    mockCounts.add(new Object[] { date, 3L });
	    
        when(auditLogService.getDailyLogCounts()).thenReturn(mockCounts);

        ResponseEntity<List<Object[]>> response = auditLogController.getDailyLogCounts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
    
}
