package com.capgemini.complaintsmanagementsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.complaintsmanagementsystem.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByComplaint_ComplaintIdOrderByChatTimestampDesc(Long complaintId);
}
