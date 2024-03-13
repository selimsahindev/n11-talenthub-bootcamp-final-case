package com.selimsahin.restaurantservice.exception.handler;

import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.kafka.producer.LogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final LogProducer logProducer;

    @ExceptionHandler
    public ResponseEntity<Map> handleAllExceptions(RuntimeException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        logProducer.publishErrorLog(exception.getMessage());
        System.out.println("Error log published");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        logProducer.publishErrorLog(exception.getMessage());
        System.out.println("Error log published");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
