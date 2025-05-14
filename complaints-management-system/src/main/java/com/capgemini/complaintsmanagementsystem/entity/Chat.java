package com.capgemini.complaintsmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat")
public class Chat {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ChatID")
	@Column(name = "ChatID")
	@NotNull(message = "ChatID is required")
    private Long chatId;

	@JsonProperty("ComplaintID")
	@Column(name = "ComplaintID")
	@NotNull(message = "ComplaintID is required")
    private Long complaintId;

    private Boolean chatSender;     
    private Boolean chatReceiver;  
    private String chatMessage;

    private LocalDateTime chatTimestamp;

    
    public Chat() {}

    public Chat(Long complaintId, Boolean chatSender, Boolean chatReceiver,
                       String chatMessage, LocalDateTime chatTimestamp) {
        this.complaintId = complaintId;
        this.chatSender = chatSender;
        this.chatReceiver = chatReceiver;
        this.chatMessage = chatMessage;
        this.chatTimestamp = chatTimestamp;
    }

  
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

    public Boolean getChatSender() {
        return chatSender;
    }

    public void setChatSender(Boolean chatSender) {
        this.chatSender = chatSender;
    }

    public Boolean getChatReceiver() {
        return chatReceiver;
    }

    public void setChatReceiver(Boolean chatReceiver) {
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
}
