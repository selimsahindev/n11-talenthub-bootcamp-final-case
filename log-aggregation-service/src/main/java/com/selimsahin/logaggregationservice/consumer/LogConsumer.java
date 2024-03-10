package com.selimsahin.logaggregationservice.consumer;

import com.selimsahin.logaggregationservice.entity.ErrorLog;
import com.selimsahin.logaggregationservice.entity.InfoLog;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final ErrorLogService errorLogService;
    private final InfoLogService infoLogService;

    @KafkaListener(topics = "${kafka.topic.error-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeErrorLog(String message){

        ErrorLog errorLog = ErrorLog.builder()
                .date(LocalDateTime.now())
                .message(message)
                .description("Error")
                .build();

        errorLogService.createErrorLog(errorLog);
    }

    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInfoLog(String message){

        InfoLog infoLog = InfoLog.builder()
                .date(LocalDateTime.now())
                .message(message)
                .description("Info")
                .build();

        infoLogService.createInfoLog(infoLog);
    }
}
