package com.selimsahin.logaggregationservice.service;

import com.selimsahin.logaggregationservice.entity.ErrorLog;
import com.selimsahin.logaggregationservice.entity.InfoLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ErrorLogService errorLogService;
    private final InfoLogService infoLogService;

    @KafkaListener(topics = "${kafka.topic.error-log}", groupId = "log-consumer-group")
    public void consumeErrorLog(String message){

        ErrorLog errorLog = ErrorLog.builder()
                .date(LocalDateTime.now())
                .message(message)
                .description("Error")
                .build();

        errorLogService.createErrorLog(errorLog);
    }

    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "log-consumer-group")
    public void consumeInfoLog(String message){

        InfoLog infoLog = InfoLog.builder()
                .date(LocalDateTime.now())
                .message(message)
                .description("Info")
                .build();

        infoLogService.createInfoLog(infoLog);
    }
}
