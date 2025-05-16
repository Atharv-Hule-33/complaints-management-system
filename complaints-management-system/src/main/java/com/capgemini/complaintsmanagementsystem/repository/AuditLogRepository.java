package com.capgemini.complaintsmanagementsystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.complaintsmanagementsystem.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{
	
	List<AuditLog> findByComplaint_ComplaintId(Long complaintId);

	List<AuditLog> findByUser_UserId(Long userId);

	@Query("SELECT a FROM AuditLog a WHERE a.auditLogTimestamp BETWEEN :start AND :end")
	List<AuditLog> findLogsBetween(LocalDateTime start, LocalDateTime end);

	@Query("SELECT FUNCTION('DATE', a.auditLogTimestamp), COUNT(a) FROM AuditLog a GROUP BY FUNCTION('DATE', a.auditLogTimestamp)")
	List<Object[]> getDailyLogCounts();
	
}
