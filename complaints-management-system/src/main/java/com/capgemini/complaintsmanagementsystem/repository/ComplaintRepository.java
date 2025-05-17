package com.capgemini.complaintsmanagementsystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.complaintsmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.entity.User;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	@Query("SELECT FUNCTION('DATE', c.complaintFiledDate), COUNT(c) FROM Complaint c GROUP BY FUNCTION('DATE', c.complaintFiledDate)")
	List<Object[]> getDailyComplaintCounts();

	@Query("SELECT ct.complaintSeverity, COUNT(c.complaintId) " + "FROM Complaint c " + "JOIN c.complaintType ct "
			+ "GROUP BY ct.complaintSeverity")

	List<Object[]> countComplaintsBySeverity();

	@Query("SELECT DATE(c.complaintFiledDate), COUNT(c.complaintId) " + "FROM Complaint c "
			+ "GROUP BY DATE(c.complaintFiledDate) " + "ORDER BY DATE(c.complaintFiledDate)")
	List<Object[]> countComplaintsByDate();

	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'FILED'")
	Long countFiledComplaints();

	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'CLOSED'")
	Long countClosedComplaints();

	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'IN_PROGRESS'")
	Long countInProgressComplaints();

	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'RESOLVED'")
    Long countResolvedComplaints();

    @Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'RESOLVED'")
    Long countResolvedComplaints();
    
    long countByUser(User user);
    long countByUserAndComplaintStatus(User user, String status);

	List<Complaint> findTop5ByUserOrderByComplaintFiledDateDesc(User user);

	@Query("""
			    SELECT c FROM Complaint c
			    WHERE (:status IS NULL OR c.complaintStatus = :status)
			      AND (:departmentId IS NULL OR c.department.departmentId = :departmentId)
			      AND (:typeId IS NULL OR c.complaintType.complaintTypeId = :typeId)
			      AND (:startDate IS NULL OR c.complaintFiledDate >= :startDate)
			      AND (:endDate IS NULL OR c.complaintFiledDate <= :endDate)
			""")
	List<Complaint> findFilteredComplaints(String status, Long departmentId, Long typeId, LocalDateTime startDate,
			LocalDateTime endDate);
    List<Complaint> findTop5ByUserOrderByComplaintFiledDateDesc(User user);



}