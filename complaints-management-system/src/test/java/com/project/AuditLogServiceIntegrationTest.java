package com.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication;
import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AuditLogServiceIntegrationTest {

	@Autowired
	private AuditLogService auditLogService;
	/*
	@Test
	@DisplayName("Should Save and retrive an AuditLog")
	void testSaveandRetriveAuditLog() {
		AuditLog log = new AuditLog(2L, 2L, "Nothing", LocalDateTime.now());
		AuditLog savedLog = auditLogService.addAuditLog(log);
		
		AuditLog retrievedLog = auditLogService.getAuditLogById(savedLog.getLogId());
		assertDoesNotThrow( () -> auditLogService.getAuditLogById(savedLog.getLogId()), "No log with ID : " + savedLog.getLogId());
		assertEquals(savedLog.getActionTaken(), retrievedLog.getActionTaken());
		assertEquals(savedLog.getUserId(), retrievedLog.getUserId());
		assertEquals(savedLog.getComplaintId(), retrievedLog.getComplaintId());
	}
	@Test
	@DisplayName("Should Save and retrive All AuditLog")
	void testSaveandRetriveAuditLogAll() {
		AuditLog log = new AuditLog(3L, 3L, "Nothing", LocalDateTime.now());
		AuditLog savedLog = auditLogService.addAuditLog(log);
		System.out.println(savedLog);
		List<AuditLog> retrievedLogs = auditLogService.getAllAuditLog();
		assertDoesNotThrow( () -> auditLogService.getAuditLogById(savedLog.getLogId()), "No log with ID : " + savedLog.getLogId());
		assertEquals(savedLog.getActionTaken(), retrievedLogs.get(0).getActionTaken());
		assertEquals(savedLog.getUserId(), retrievedLogs.get(0).getUserId());
		assertEquals(savedLog.getComplaintId(), retrievedLogs.get(0).getComplaintId());
	}
	*/
	
}
