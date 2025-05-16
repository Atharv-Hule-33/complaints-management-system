package com.capgemini.complaintsmanagementsystem.service;

import java.util.List;
import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.entity.Chat;

public interface ChatService {
	//List<Chat> getChatHistoryByComplaintId(Long complaintId);

	List<Chat> getChatHistoryBetweenUsers(String sender, String receiver);

	//Chat saveAndSendMessage(ChatMessageDTO chatMessageDTO);

	//Chat addChatMessage(ChatMessageDTO chatMessageDTO);

	List<Chat> getMessagesBySender(String sender);

	List<Chat> getAllMessages();

}
