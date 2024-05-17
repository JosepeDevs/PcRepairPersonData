package com.josepdevs.Domain.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice //lobal exception handler. This class can contain multiple methods, each annotated with @ExceptionHandler, to handle different types of exceptions.
@Log4j2
public class GlobalExceptionHandler {
	
	//CENTRALIZED EXCEPTION MANAGER, built-in and personalized exceptions, gathers errors in a list of maps and returns when something goes wrong
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<Map<String, String>>> handleDeserializationError(HttpMessageNotReadableException ex) {
       //create the list of maps
		List<Map<String, String>> errorsMapList = new ArrayList<>();
        //create the map that will contain these errors and will get inside the list.
		Map<String, String> errorDetails = new HashMap<>();
		//put in the map this generic message, for each exception we will personalize it
		errorDetails.put("error", "Serialization of the received JSON body failed, maybe some validation of the valueObjects failed.");
		//add to the list
		errorsMapList.add(errorDetails);
        //return in the body the erro
        return new ResponseEntity<>(errorsMapList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handlePasswordNotValidError(PasswordNotValidException ex) {
        List<Map<String, String>> errorsMapList = new ArrayList<>();
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("password", "Password did not meet either the required lower case, upper case and symbol requirement or the length was not enough");
        errorsMapList.add(errorDetails);
        return new ResponseEntity<>(errorsMapList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<List<Map<String, String>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        List<Map<String, String>> errorsMapList = new ArrayList<>();
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "An illegal argument was provided.");
        errorsMapList.add(errorDetails);
        return new ResponseEntity<>(errorsMapList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<List<Map<String, String>>> handleNullPointerException(NullPointerException ex) {
        List<Map<String, String>> errorsMapList = new ArrayList<>();
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "A null pointer exception occurred.");
        errorsMapList.add(errorDetails);
        return new ResponseEntity<>(errorsMapList, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    
    
}