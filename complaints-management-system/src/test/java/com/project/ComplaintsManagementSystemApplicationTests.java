package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication;
import com.capgemini.complaintsmanagementsystem.controller.AuditLogController;
import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
 class ComplaintsManagementSystemApplicationTests {
	
	@Mock
	private AuditLogService auditLogService;
	
	@InjectMocks
	private AuditLogController auditLogController;

    @Test
    @DisplayName("Get Audit Log")
    void getAllAuditLog() {
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
    }

}
