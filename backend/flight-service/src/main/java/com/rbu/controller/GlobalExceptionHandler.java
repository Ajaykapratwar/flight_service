package com.rbu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rbu.service.InvalidFlightException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidFlightException.class)
	public ResponseEntity<String> handleInvalidFlight(InvalidFlightException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
