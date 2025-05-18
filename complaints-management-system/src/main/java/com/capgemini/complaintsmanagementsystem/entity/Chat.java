package com.capgemini.complaintsmanagementsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id")
	private Long chatId;

	@OneToOne
	@JoinColumn(name = "complaint_id", nullable = false)
	private Complaint complaint;

	@Column(name = "chat_sender")
	private String chatSender;

	@Column(name = "chat_receiver")
	private String chatReceiver;

	@Column(name = "chat_message", columnDefinition = "TEXT")
	private String chatMessage;

	@Column(name = "chat_timestamp")
	private LocalDateTime chatTimestamp;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
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

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public Chat(Complaint complaint, String chatSender, String chatReceiver, String chatMessage,
			LocalDateTime chatTimestamp) {
		super();
		this.complaint = complaint;
		this.chatSender = chatSender;
		this.chatReceiver = chatReceiver;
		this.chatMessage = chatMessage;
		this.chatTimestamp = chatTimestamp;
	}

	public Chat() {
		super();
	}
}
