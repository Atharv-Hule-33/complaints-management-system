package com.capgemini.complaintsmanagementsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.complaintsmanagementsystem.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByComplaintId(Long complaintId);
    List<Chat> findByChatSenderAndChatReceiver(String chatSender, String chatReceiver);
	List<Chat> findByComplaintIdOrderByChatTimestampAsc(Long complaintId);
	List<Chat> findByChatSender(String chatSender);
	List<Chat> findByChatSenderOrderByChatTimestampDesc(String sender);
	List<Chat> findAllByOrderByChatTimestampDesc();

}
