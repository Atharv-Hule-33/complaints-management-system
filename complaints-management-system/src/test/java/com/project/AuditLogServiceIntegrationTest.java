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
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.entity.User;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.capgemini.complaintsmanagementsystem.repository.UserRepository;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AuditLogServiceIntegrationTest {

	@Autowired
	private AuditLogService auditLogService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComplaintRepository complaintRepository;
	
	private AuditLog buildLog(Long complaintId, Long userId, String action) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(complaintId);
        complaint = complaintRepository.save(complaint);

        User user = new User();
        user.setUserId(userId);
        user = userRepository.save(user);

        return new AuditLog(
                complaint,
                user,
                action,
                LocalDateTime.now()
        );
    }
	
	@Test
	@DisplayName("Should Save and retrive an AuditLog")
	void testSaveandRetriveAuditLog() {
        AuditLog log = buildLog(1L, 1L, "Created complaint");
		AuditLog savedLog = auditLogService.addAuditLog(log);
		
		AuditLog retrievedLog = auditLogService.getAuditLogById(savedLog.getLogId());
		assertDoesNotThrow( () -> auditLogService.getAuditLogById(savedLog.getLogId()), "No log with ID : " + savedLog.getLogId());
		assertEquals(savedLog.getActionTaken(), retrievedLog.getActionTaken());
		assertEquals(savedLog.getUser(), retrievedLog.getUser());
		assertEquals(savedLog.getComplaint(), retrievedLog.getComplaint());
	}
	
	@Test
	@DisplayName("Should Save and retrive All AuditLog")
	void testSaveandRetriveAuditLogAll() {
        AuditLog log = buildLog(1L, 1L, "Created complaint");
		AuditLog savedLog = auditLogService.addAuditLog(log);
		System.out.println(savedLog);
		List<AuditLog> retrievedLogs = auditLogService.getAllAuditLog();
		assertDoesNotThrow( () -> auditLogService.getAuditLogById(savedLog.getLogId()), "No log with ID : " + savedLog.getLogId());
		assertEquals(savedLog.getActionTaken(), retrievedLogs.get(0).getActionTaken());
		assertEquals(savedLog.getUser(), retrievedLogs.get(0).getUser());
		assertEquals(savedLog.getComplaint(), retrievedLogs.get(0).getComplaint());
	}
	
}
