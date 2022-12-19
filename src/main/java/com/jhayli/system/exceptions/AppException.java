package com.jhayli.system.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;
	
	private HttpStatus status;
	
	private List<String> errors;
	
	public AppException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public AppException(String message, HttpStatus status, List<String> errors) {
		this.message = message;
		this.status = status;
		this.errors = errors;
	}

	
	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	public List<String> getErrors() {
		return errors;
	}
}
