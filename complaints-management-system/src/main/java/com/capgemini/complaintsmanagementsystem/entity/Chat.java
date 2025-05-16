package com.capgemini.complaintsmanagementsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "chat")
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;
    
    @NotNull(message = "Complaint ID cannot be null")
    @Column(name = "complaint_id")
    private Long complaintId;
    
    @NotBlank(message = "Sender cannot be empty")
    @Column(name = "chat_sender")
    private String chatSender;
    
    @NotBlank(message = "Receiver cannot be empty")
    @Column(name = "chat_receiver")
    private String chatReceiver;
    
    @NotBlank(message = "Message cannot be empty")
    @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters")
    @Column(name = "chat_message", columnDefinition = "TEXT")
    private String chatMessage;
    
    @NotNull(message = "Timestamp cannot be null")
    @Column(name = "chat_timestamp")
    private LocalDateTime chatTimestamp;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public String getChatSender() {
		return chatSender;
	}

	public void setChatSender(String chatSender) {
		this.chatSender = chatSender;
	}

	public String getChatReceiver() {
		return chatReceiver;
	}

	public void setChatReceiver(String chatReceiver) {
		this.chatReceiver = chatReceiver;
	}

	public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

	public LocalDateTime getChatTimestamp() {
		return chatTimestamp;
	}

	public void setChatTimestamp(LocalDateTime chatTimestamp) {
		this.chatTimestamp = chatTimestamp;
	}

	public Chat(Long chatId, Long complaintId, String chatSender, String chatReceiver, String chatMessage,
			LocalDateTime chatTimestamp) {
		super();
		this.chatId = chatId;
		this.complaintId = complaintId;
		this.chatSender = chatSender;
		this.chatReceiver = chatReceiver;
		this.chatMessage = chatMessage;
		this.chatTimestamp = chatTimestamp;
	}

	public Chat() {
		super();
	}
    
	
    
}
