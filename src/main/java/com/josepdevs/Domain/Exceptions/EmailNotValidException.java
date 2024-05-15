package com.josepdevs.Domain.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.log4j.Log4j2;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Log4j2
public class EmailNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotValidException(String email, String message) {
		super(message);
		log.error(email,"email format was not valid");
	}
	
}
