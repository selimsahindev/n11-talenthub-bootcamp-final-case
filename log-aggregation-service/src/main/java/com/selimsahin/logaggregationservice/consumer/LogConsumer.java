package com.selimsahin.logaggregationservice.consumer;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.dto.InfoLogDTO;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
    public void consumeErrorLog(ConsumerRecord<String, String> message){

        try {
            ErrorLogDTO errorLog = ErrorLogDTO.builder()
                    .service(message.key())
                    .timestamp(LocalDateTime.now())
                    .status(200)
                    .error(message.value())
                    .message(message.value())
                    .stackTrace(message.value())
                    .build();

            errorLogService.createErrorLog(errorLog);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInfoLog(ConsumerRecord<String, String> message){

        try {
            InfoLogDTO infoLog = InfoLogDTO.builder()
                    .service(message.key())
                    .timestamp(LocalDateTime.now())
                    .message(message.value())
                    .description(message.value())
                    .build();

            infoLogService.createInfoLog(infoLog);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
