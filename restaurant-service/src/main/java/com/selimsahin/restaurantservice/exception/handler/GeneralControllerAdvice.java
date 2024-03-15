package com.selimsahin.restaurantservice.exception.handler;

import com.selimsahin.restaurantservice.dto.ErrorLogDTO;
import com.selimsahin.restaurantservice.exception.LogProducerException;
import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.kafka.producer.LogProducer;
import com.selimsahin.restaurantservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
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

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(LogProducerException.class)
    public ResponseEntity<Map> handleLogProducerException(LogProducerException exception) {

        Map<String, String> response = new HashMap<>();
        response.put(messageKey, exception.getMessage());

        appLogger.logError(exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
