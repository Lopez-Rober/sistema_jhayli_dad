package com.jhayli.system.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends AppException {
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String message) {
		super(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
