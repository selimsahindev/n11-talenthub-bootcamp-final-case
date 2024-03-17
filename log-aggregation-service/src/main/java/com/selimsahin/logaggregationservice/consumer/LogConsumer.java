package com.selimsahin.logaggregationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.error-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeErrorLog(ConsumerRecord<String, String> payload){

        try {
            ErrorLogDTO errorLogDto = objectMapper.readValue(payload.value(), ErrorLogDTO.class);
            errorLogService.createErrorLog(errorLogDto);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInfoLog(ConsumerRecord<String, String> payload){

        try {
            InfoLogDTO infoLogDto = objectMapper.readValue(payload.value(), InfoLogDTO.class);
            infoLogService.createInfoLog(infoLogDto);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
