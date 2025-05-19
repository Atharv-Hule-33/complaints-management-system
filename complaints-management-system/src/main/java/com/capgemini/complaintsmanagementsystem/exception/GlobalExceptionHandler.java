package com.capgemini.complaintsmanagementsystem.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String TIMESTAMP_KEY = "timestamp";
	private static final String MESSAGE_KEY = "message";
	private static final String STATUS_KEY = "status";
	private static final String DETAILS_KEY = "details";
	private static final String UNEXPECTED_ERROR = "Unexpected error occurred";

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ChatNotFoundException.class)
	public ResponseEntity<Object> handleChatNotFound(ChatNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ChatSenderNotFoundException.class)
	public ResponseEntity<Object> handleChatSenderNotFound(ChatSenderNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidChatRequestException.class)
	public ResponseEntity<Object> handleInvalidChatRequest(InvalidChatRequestException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Object> handleDepartmentNotFound(DepartmentNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(AuditLogNotFoundException.class)
	public ResponseEntity<Object> handleAuditLogNotFound(AuditLogNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(STATUS_KEY, status.value());

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

		errorDetails.put("errors", fieldErrors);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	

	
	@ExceptionHandler(ComplaintTypeNotFoundException.class)
	public ResponseEntity<Object> handleComplaintTypeNotFound(ComplaintTypeNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, UNEXPECTED_ERROR);
		errorDetails.put(DETAILS_KEY, ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, UNEXPECTED_ERROR);
		errorDetails.put(DETAILS_KEY, ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, UNEXPECTED_ERROR);
		errorDetails.put(DETAILS_KEY, ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ComplaintNotfoundException.class)
	public ResponseEntity<Object> handleComplaintNotFound(ComplaintNotfoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP_KEY, LocalDateTime.now());
		errorDetails.put(MESSAGE_KEY, ex.getMessage());
		errorDetails.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
