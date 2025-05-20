package com.project;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication;
import com.capgemini.complaintsmanagementsystem.controller.AuditLogController;
import com.capgemini.complaintsmanagementsystem.service.AuditLogService;

@SpringBootTest(classes = ComplaintsManagementSystemApplication.class)
 class ComplaintsManagementSystemApplicationTests {
	
	@Mock
	private AuditLogService auditLogService;
	
	@InjectMocks
	private AuditLogController auditLogController;



}
