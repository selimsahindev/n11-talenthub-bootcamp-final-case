package com.selimsahin.recommendationservice.exception.handler;

import com.selimsahin.recommendationservice.exception.LogProducerException;
import com.selimsahin.recommendationservice.exception.SolrQueryException;
import com.selimsahin.recommendationservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class GeneralControllerAdvice {

    private final AppLogger appLogger;

    @ExceptionHandler
    public ResponseEntity<Map> handleAllExceptions(RuntimeException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(SolrQueryException.class)
    public ResponseEntity<Map> handleSolrQueryException(SolrQueryException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(LogProducerException.class)
    public ResponseEntity<Map> handleLogProducerException(LogProducerException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // Extracting and showing only the default message for Range constraint violation
        if (exception.getBindingResult().getFieldError("rate") != null) {
            response.put("message", exception.getBindingResult().getFieldError("rate").getDefaultMessage());
        } else {
            response.put("message", "Validation failed. Please check your request.");
        }

        appLogger.logError(exception);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
