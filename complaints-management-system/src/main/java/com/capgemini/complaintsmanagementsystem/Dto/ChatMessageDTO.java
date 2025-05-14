package com.capgemini.complaintsmanagementsystem.Dto;

public class ChatMessageDTO {
	private Long complaintId;
	private String message;
	private String sender;
	private String receiver;

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public ChatMessageDTO(Long complaintId, String message, String sender, String receiver) {
		super();
		this.complaintId = complaintId;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public ChatMessageDTO() {
		super();
	}

}
