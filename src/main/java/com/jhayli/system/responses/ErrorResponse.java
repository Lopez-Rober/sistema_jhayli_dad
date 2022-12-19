package com.jhayli.system.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private String message;
	
	private HttpStatus status;
	
	private List<String> errors;

	public ErrorResponse(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public ErrorResponse(String message, HttpStatus status, List<String> errors) {
		this.message = message;
		this.status = status;
		this.errors = errors;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
