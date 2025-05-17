package com.capgemini.complaintsmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.entity.User;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
	@Query("SELECT FUNCTION('DATE', c.complaintFiledDate), COUNT(c) FROM Complaint c GROUP BY FUNCTION('DATE', c.complaintFiledDate)")
	List<Object[]> getDailyComplaintCounts();


    @Query("SELECT ct.complaintSeverity, COUNT(c.complaintId) " +
            "FROM Complaint c " +
            "JOIN c.complaintType ct " +
            "GROUP BY ct.complaintSeverity")

List<Object[]> countComplaintsBySeverity();


    @Query("SELECT DATE(c.complaintFiledDate), COUNT(c.complaintId) " +
            "FROM Complaint c " +
            "GROUP BY DATE(c.complaintFiledDate) " +
            "ORDER BY DATE(c.complaintFiledDate)")
    List<Object[]> countComplaintsByDate();

    @Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'FILED'")
    Long countFiledComplaints();

    @Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'CLOSED'")
    Long countClosedComplaints();

    @Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'IN_PROGRESS'")
    Long countInProgressComplaints();

    @Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.complaintStatus = 'RESOLVED'")
    Long countResolvedComplaints();

    
    long countByUser(User user);
    long countByUserAndComplaintStatus(User user, String status);
    List<Complaint> findTop5ByUserOrderByComplaintFiledDateDesc(User user);



}