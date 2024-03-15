package com.selimsahin.restaurantservice.util;

import com.selimsahin.restaurantservice.dto.ErrorLogDTO;
import com.selimsahin.restaurantservice.dto.InfoLogDTO;
import com.selimsahin.restaurantservice.kafka.producer.LogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Component
@RequiredArgsConstructor
public class AppLogger {

    @Value("${spring.application.name}")
    private String serviceName;

    private final LogProducer logProducer;

    public void logError(Exception exception) {

        ErrorLogDTO errorLogDTO = ErrorLogDTO.builder()
                .service(serviceName)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error(exception.getClass().getName())
                .message(exception.getMessage())
                .stackTrace(convertStackTraceToString(exception.getStackTrace()))
            .build();

        logProducer.produceErrorLog(errorLogDTO);
    }

    public void logInfo(String message, String description) {

        InfoLogDTO infoLogDTO = InfoLogDTO.builder()
                .service(serviceName)
                .timestamp(LocalDateTime.now())
                .message(message)
                .description(description)
                .build();

        logProducer.produceInfoLog(infoLogDTO);
    }

    private String convertStackTraceToString(StackTraceElement[] stackTrace) {

        StringBuilder sb = new StringBuilder();

        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString()).append("\n");
        }

        return sb.toString();
    }
}
