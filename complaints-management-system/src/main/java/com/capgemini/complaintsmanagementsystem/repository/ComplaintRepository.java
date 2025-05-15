package com.capgemini.complaintsmanagementsystem.repository;

import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("select c.complaintSeverity,count(cs.complaintId) as complaintCount from ComplaintType c left join Complaint cs on c.complaintTypeId = cs.complaintTypeId group by c.complaintTypeId,c.complaintSeverity order by complaintCount desc")
    List<Object[]> countComplaintsBySeverity();


}
