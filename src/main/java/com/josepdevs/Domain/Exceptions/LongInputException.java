package com.josepdevs.Domain.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.log4j.Log4j2;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Log4j2
public class LongInputException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	//pasamos el mensaje a RunTimeException y logeamos el error
	public LongInputException(String input, String message) {
        super(message);
        log.error("Input not valid: too many characters",input);
    }
	

}
