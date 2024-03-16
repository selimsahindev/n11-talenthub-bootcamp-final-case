package com.selimsahin.recommendationservice.exception.handler;

import com.selimsahin.recommendationservice.dto.response.RestResponse;
import com.selimsahin.recommendationservice.exception.LogProducerException;
import com.selimsahin.recommendationservice.exception.SolrQueryException;
import com.selimsahin.recommendationservice.exception.errormessages.GeneralErrorMessage;
import com.selimsahin.recommendationservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    @ExceptionHandler(SolrQueryException.class)
    public ResponseEntity<Object> handleSolrQueryException(SolrQueryException exception, WebRequest request) {

        appLogger.logError(exception);

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }

    @ExceptionHandler(LogProducerException.class)
    public ResponseEntity<Object> handleLogProducerException(LogProducerException exception, WebRequest request) {

        appLogger.logError(exception);

        RestResponse<GeneralErrorMessage> restResponse = getGeneralErrorMessageRestResponse(exception, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        appLogger.logError(exception);

        List<Map<String, String>> errorList = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {

                    Map<String, String> errorMap = new HashMap<>();

                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

                    return errorMap;
                })
                .toList();

        String description = request.getDescription(false);

        GeneralErrorMessage generalErrorMessage = new GeneralErrorMessage(
                LocalDateTime.now(),
                errorList.toString(),
                description
        );

        RestResponse<GeneralErrorMessage> restResponse = RestResponse.error(generalErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
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
