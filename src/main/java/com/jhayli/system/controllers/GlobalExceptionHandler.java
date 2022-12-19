package com.jhayli.system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jhayli.system.exceptions.AppException;
import com.jhayli.system.exceptions.InternalServerErrorException;
import com.jhayli.system.responses.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception exception) {
		AppException appException;

		if (exception instanceof AppException) {
			appException = (AppException) exception;
		} else {
			exception.printStackTrace();
	        appException = new InternalServerErrorException("Internal Server Error");
		}
		System.out.println( appException.getStatus());

		ErrorResponse response = new ErrorResponse(appException.getMessage(), appException.getStatus(), appException.getErrors());

		return new ResponseEntity<>(response, response.getStatus());

	}
}
