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
	
	@Query("""
		    SELECT al
		    FROM AuditLog al
		    WHERE (:complaintId IS NULL OR al.complaint.complaintId = :complaintId)
		      AND (:userId IS NULL OR al.user.userId = :userId)
		      AND (:departmentId IS NULL OR al.complaint.department.departmentId = :departmentId)
		      AND (:complaintTypeId IS NULL OR al.complaint.complaintType.complaintTypeId = :complaintTypeId)
		      AND (:actionTaken IS NULL OR al.actionTaken = :actionTaken)
		      AND (:startDate IS NULL OR al.auditLogTimestamp >= :startDate)
		      AND (:endDate IS NULL OR al.auditLogTimestamp <= :endDate)
		""")
		List<AuditLog> findFilteredAuditLogs(Long complaintId,
		                                     Long userId,
		                                     Long departmentId,
		                                     Long complaintTypeId,
		                                     String actionTaken,
		                                     LocalDateTime startDate,
		                                     LocalDateTime endDate);
	
}
