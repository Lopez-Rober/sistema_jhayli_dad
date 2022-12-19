package com.jhayli.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends AppException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
