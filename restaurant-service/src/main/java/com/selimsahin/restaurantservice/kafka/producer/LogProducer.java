package com.selimsahin.restaurantservice.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.restaurantservice.dto.ErrorLogDTO;
import com.selimsahin.restaurantservice.dto.InfoLogDTO;
import com.selimsahin.restaurantservice.exception.LogProducerException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class LogProducer {

    @Value("${kafka.topic.error-log}")
    private String errorLogTopic;

    @Value("${kafka.topic.info-log}")
    private String infoLogTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void produceErrorLog(ErrorLogDTO errorLogDTO) {
        try {
            // Serialize the errorLogDTO and send it to the errorLogTopic
            String errorLogJson = objectMapper.writeValueAsString(errorLogDTO);

            // Send the errorLogJson to the errorLogTopic
            kafkaTemplate.send(new ProducerRecord<>(errorLogTopic, errorLogJson));

        } catch (Exception e) {
            throw new LogProducerException();
        }
    }

    public void produceInfoLog(InfoLogDTO infoLogDTO) {
        try {
            // Serialize the infoLogDTO and send it to the errorLogTopic
            String infoLogJson = objectMapper.writeValueAsString(infoLogDTO);

            // Send the infoLogJson to the errorLogTopic
            kafkaTemplate.send(new ProducerRecord<>(infoLogTopic, infoLogJson));

        } catch (Exception e) {
            throw new LogProducerException();
        }
    }
}
