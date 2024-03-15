package com.selimsahin.userservice.util;

import com.selimsahin.userservice.dto.ErrorLogDTO;
import com.selimsahin.userservice.dto.InfoLogDTO;
import com.selimsahin.userservice.producer.LogProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Component
@Slf4j
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
        log.error("Error log produced: " + errorLogDTO);
    }

    public void logInfo(String message, String description) {

        InfoLogDTO infoLogDTO = InfoLogDTO.builder()
                .service(serviceName)
                .timestamp(LocalDateTime.now())
                .message(message)
                .description(description)
                .build();

        logProducer.produceInfoLog(infoLogDTO);
        log.info("Info log produced: " + infoLogDTO);
    }

    private String convertStackTraceToString(StackTraceElement[] stackTrace) {

        StringBuilder sb = new StringBuilder();

        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString()).append("\n");
        }

        return sb.toString();
    }
}
