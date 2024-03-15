package com.selimsahin.userservice.exception.handler;

import com.selimsahin.userservice.exception.LogProducerException;
import com.selimsahin.userservice.exception.RestaurantNotFoundException;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.exception.UserReviewNotFoundException;
import com.selimsahin.userservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class GeneralControllerAdvice {

    private final AppLogger appLogger;
    private final String messageKey = "message";

    @ExceptionHandler
    public ResponseEntity<Map> handleAllExceptions(RuntimeException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(LogProducerException.class)
    public ResponseEntity<Map> handleLogProducerException(LogProducerException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map> handleUserNotFoundException(UserNotFoundException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserReviewNotFoundException.class)
    public ResponseEntity<Map> handleUserReviewNotFoundException(UserReviewNotFoundException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // Extracting and showing only the default message for Range constraint violation
        if (exception.getBindingResult().getFieldError("rate") != null) {
            response.put(messageKey, exception.getBindingResult().getFieldError("rate").getDefaultMessage());
        } else {
            response.put(messageKey, "Validation failed. Please check your request.");
        }

        appLogger.logError(exception);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
