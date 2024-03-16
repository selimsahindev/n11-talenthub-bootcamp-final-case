package com.selimsahin.restaurantservice.exception.handler;

import com.selimsahin.restaurantservice.dto.ErrorLogDTO;
import com.selimsahin.restaurantservice.dto.response.RestResponse;
import com.selimsahin.restaurantservice.exception.LogProducerException;
import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.exception.errormessages.GeneralErrorMessage;
import com.selimsahin.restaurantservice.kafka.producer.LogProducer;
import com.selimsahin.restaurantservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author selimsahindev
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    private final AppLogger appLogger;

    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(RuntimeException exception, WebRequest request) {

        appLogger.logError(exception);

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Object> handleRestaurantNotFoundException(RestaurantNotFoundException exception,
                                                                 WebRequest request) {

        appLogger.logError(exception);

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
    }

    @ExceptionHandler(LogProducerException.class)
    public ResponseEntity<Object> handleLogProducerException(LogProducerException exception,
                                                          WebRequest request) {

        appLogger.logError(exception);

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
    }

    private static RestResponse<GeneralErrorMessage> getGeneralErrorMessageRestResponse(RuntimeException exception,
                                                                                        WebRequest request) {

        String message = exception.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessage generalErrorMessages =
                new GeneralErrorMessage(LocalDateTime.now(), message, description);

        return RestResponse.error(generalErrorMessages);
    }
}
