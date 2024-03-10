package com.selimsahin.userservice.producer;

import com.selimsahin.userservice.dto.AverageRatingUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topic.average-rating-update}")
    private String averageRatingUpdateTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishAverageRatingCalculatedEvent(Long restaurantId, Double averageRating) {

        AverageRatingUpdateDTO averageRatingUpdateDTO = new AverageRatingUpdateDTO(restaurantId, averageRating);
        kafkaTemplate.send(averageRatingUpdateTopic, averageRatingUpdateDTO);
    }
}
