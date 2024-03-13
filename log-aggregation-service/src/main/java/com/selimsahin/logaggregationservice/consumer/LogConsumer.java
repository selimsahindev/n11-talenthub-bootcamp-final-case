package com.selimsahin.logaggregationservice.consumer;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.entity.ErrorLog;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
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
//    private final InfoLogService infoLogService;

    @KafkaListener(topics = "${kafka.topic.error-log}")
    public void consumeErrorLog(String message){

        ErrorLogDTO errorLogDto = ErrorLogDTO.builder()
                .date(LocalDateTime.now())
                .message(message)
                .description("Error")
                .build();

        errorLogService.createErrorLog(errorLogDto);
    }

//    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumeInfoLog(String message){
//
//        InfoLog infoLog = InfoLog.builder()
//                .date(LocalDateTime.now())
//                .message(message)
//                .description("Info")
//                .build();
//
//        infoLogService.saveInfoLog(infoLog);
//    }
}
