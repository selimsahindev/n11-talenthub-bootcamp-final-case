package com.selimsahin.userreviewservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author selimsahindev
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map> handleAllExceptions(RuntimeException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // Extracting and showing only the default message for Range constraint violation
        if (ex.getBindingResult().getFieldError("rate") != null) {
            response.put("message", ex.getBindingResult().getFieldError("rate").getDefaultMessage());
        } else {
            response.put("message", "Validation failed. Please check your request.");
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
