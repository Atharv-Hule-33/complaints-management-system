package com.capgemini.complaintsmanagementsystem.exception;


public class InvalidChatRequestException extends RuntimeException {
    public InvalidChatRequestException(String message) {
        super(message);
    }
}
