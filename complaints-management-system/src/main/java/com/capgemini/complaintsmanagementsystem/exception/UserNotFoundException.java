package com.capgemini.complaintsmanagementsystem.exception;


public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
