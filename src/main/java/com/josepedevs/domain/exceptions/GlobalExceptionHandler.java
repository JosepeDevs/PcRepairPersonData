package com.josepedevs.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // MY CUSTOMIZED EXCEPTIONS

    @ExceptionHandler(MyRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleMyExceptions(MyRuntimeException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put(
                "The attribute with name: '" + ex.getIllegalAttributeName() + "' was not valid",
                ex.getMyErrorMessage());
        return ResponseEntity.status(ex.getStatus().getStatus()).body(errorDetails);
    }

    // BUILT-IN EXCEPTIONS HANDLING TO NOW SHOW STACKTRACE

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> globalExceptionManager(Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        final var msg= String.format("A problem happened: %s", ex.getLocalizedMessage());
        errorDetails.put("Exception", msg);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> myNoResourceFoundException(NoResourceFoundException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put(
                "NoResourceFoundException", "The resouce you were looking for does not exist or has been removed.");
        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<Map<String, String>> jpaException(JpaSystemException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        final var msg= String.format(
            "There was a problem when persisting your data, possible action: review name of attributes sent. more info: %s",
            ex.getLocalizedMessage()
        );
        errorDetails.put("PersistException", msg);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> myOwnHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        final var msg= String.format(
                "A problem with the HTTP message occurred and cannot be read properly, maybe it could not be parsed correctly to/from JSON, check content of body. more info: %s",
                ex.getLocalizedMessage()
        );
        errorDetails.put("Http Error", msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        final var msg= String.format(
                "The argument or parameter that was in use or used was not expected (illegal argument). more info: %s",
                ex.getLocalizedMessage()
        );
        errorDetails.put("Illegal argument error", msg);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        final var msg= String.format(
                "Null resource error. more info: %s",
                ex.getLocalizedMessage()
        );
        errorDetails.put("Null resource error", msg);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }
}
