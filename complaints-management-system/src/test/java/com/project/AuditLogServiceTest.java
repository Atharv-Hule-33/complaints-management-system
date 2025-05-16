package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;
import com.capgemini.complaintsmanagementsystem.repository.AuditLogRepository;
import com.capgemini.complaintsmanagementsystem.service.AuditLogServiceImp;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceTest {
	
	@Mock
	AuditLogRepository auditLogRepository;
	
	@InjectMocks
	AuditLogServiceImp auditLogService;
	/*
	@Test
	void testAddAuditLog() {
		
		AuditLog log = new AuditLog();
		log.getLogId();
		log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());
        
        when(auditLogRepository.save(log)).thenReturn(log);
        
        AuditLog result = auditLogService.addAuditLog(log);
        assertEquals("Created complaint", result.getActionTaken());
        assertEquals(1L, result.getComplaintId());
        assertEquals(1L, result.getUserId());
		
	}
	
	@Test
	void testGetAuditLogById() {
		
		AuditLog log = new AuditLog();
		log.getLogId();
		log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());
        
        when(auditLogRepository.findById(1L)).thenReturn(Optional.of(log));
        
        AuditLog result = auditLogService.getAuditLogById(1L);
        assertEquals("Created complaint", result.getActionTaken());
        assertEquals(1L, result.getComplaintId());
        assertEquals(1L, result.getUserId());
		
	}
	
	@Test
	void testGetAllAuditLog() {
		
		AuditLog log = new AuditLog();
		log.getLogId();
		log.setComplaintId(1L);
        log.setUserId(1L);
        log.setActionTaken("Created complaint");
        log.setAuditLogTimestamp(LocalDateTime.now());
        
        List<AuditLog> resultList = Arrays.asList(log);
        
        when(auditLogRepository.findAll()).thenReturn(resultList);
        
        List<AuditLog> result = auditLogService.getAllAuditLog();
        assertEquals("Created complaint", result.get(0).getActionTaken());
        assertEquals(1L, result.get(0).getComplaintId());
        assertEquals(1L, result.get(0).getUserId());
		
	}
	
	@Test
	void testGetLogsByComplaintId() {
	    AuditLog log = new AuditLog();
	    log.setComplaintId(1L);
	    log.setUserId(1L);
	    log.setActionTaken("Updated complaint");
	    log.setAuditLogTimestamp(LocalDateTime.now());

	    when(auditLogRepository.findByComplaintId(1L)).thenReturn(Arrays.asList(log));

	    List<AuditLog> result = auditLogService.getLogsByComplaintId(1L);
	    assertEquals(1, result.size());
	    assertEquals("Updated complaint", result.get(0).getActionTaken());
	}

	@Test
	void testGetLogsByUserId() {
	    AuditLog log = new AuditLog();
	    log.setComplaintId(2L);
	    log.setUserId(5L);
	    log.setActionTaken("Viewed complaint");
	    log.setAuditLogTimestamp(LocalDateTime.now());

	    when(auditLogRepository.findByUserId(5L)).thenReturn(Arrays.asList(log));

	    List<AuditLog> result = auditLogService.getLogsByUserId(5L);
	    assertEquals(1, result.size());
	    assertEquals(5L, result.get(0).getUserId());
	}

	@Test
	void testGetLogsBetweenDates() {
	    LocalDateTime start = LocalDateTime.now().minusDays(2);
	    LocalDateTime end = LocalDateTime.now();

	    AuditLog log = new AuditLog();
	    log.setComplaintId(3L);
	    log.setUserId(7L);
	    log.setActionTaken("Viewed complaint");
	    log.setAuditLogTimestamp(LocalDateTime.now());

	    when(auditLogRepository.findLogsBetween(start, end)).thenReturn(Arrays.asList(log));

	    List<AuditLog> result = auditLogService.getLogsBetween(start, end);
	    assertEquals(1, result.size());
	}*/

	@Test
	void testGetDailyLogCounts() {
		LocalDate date = LocalDate.of(2025, 1, 1);

	    List<Object[]> mockCounts = new ArrayList<>();
	    mockCounts.add(new Object[] { date, 3L });

	    Mockito.<List<Object[]>>when(auditLogRepository.getDailyLogCounts())
	           .thenReturn(mockCounts);

	    List<Object[]> result = auditLogService.getDailyLogCounts();

	    assertEquals(1, result.size());
	    assertEquals(date, result.get(0)[0]);
	    assertEquals(3L,  result.get(0)[1]);
	}
	
}
