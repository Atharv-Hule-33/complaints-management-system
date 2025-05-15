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

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFound(UserNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuditLogNotFoundException.class)
	public ResponseEntity<Object> handleAuditLogNotFound(AuditLogNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", status.value());

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

		errorDetails.put("errors", fieldErrors);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ComplaintTypeNotFoundException.class)
	public ResponseEntity<Object> handleComplaintTypeNotFound(ComplaintTypeNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", "Unexpected error occurred");
		errorDetails.put("details", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", "Unexpected error occurred");
		errorDetails.put("details", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", "Unexpected error occurred");
		errorDetails.put("details", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
