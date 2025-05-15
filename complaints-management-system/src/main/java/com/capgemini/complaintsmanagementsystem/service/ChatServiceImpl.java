package com.capgemini.complaintsmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.repository.ChatRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;
	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public ChatServiceImpl(ChatRepository chatRepository, SimpMessagingTemplate messagingTemplate) {
		this.chatRepository = chatRepository;
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public List<Chat> getChatHistoryByComplaintId(Long complaintId) {
		log.debug("Getting chat history by complaint id: {}", complaintId);
		return chatRepository.findByComplaintIdOrderByChatTimestampAsc(complaintId);
	}

	@Override
	public List<Chat> getChatHistoryBetweenUsers(String sender, String receiver) {
		log.debug("Getting chat history between users: {}", sender,receiver);
		List<Chat> sentMessages = chatRepository.findByChatSenderAndChatReceiver(sender, receiver);
		List<Chat> receivedMessages = chatRepository.findByChatSenderAndChatReceiver(receiver, sender);

		sentMessages.addAll(receivedMessages);

		sentMessages.sort((c1, c2) -> c1.getChatTimestamp().compareTo(c2.getChatTimestamp()));

		return sentMessages;
	}

	@Override
	public Chat saveAndSendMessage(ChatMessageDTO chatMessageDTO) {
	    Chat chat = new Chat();
	   
	    chat.setComplaintId(chatMessageDTO.getComplaintId());
	    chat.setChatSender(chatMessageDTO.getSender());
	    chat.setChatReceiver(chatMessageDTO.getReceiver());
	    chat.setChatMessage(chatMessageDTO.getMessage());
	    chat.setChatTimestamp(LocalDateTime.now());

	    Chat savedChat = chatRepository.save(chat);
	    messagingTemplate.convertAndSend("/topic/chat/" + chatMessageDTO.getReceiver(), chatMessageDTO);
		log.debug("Saving chat for complaint id: {}", chatMessageDTO.getComplaintId());
	    return savedChat;
	}

	

}
