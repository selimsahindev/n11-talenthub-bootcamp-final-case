package com.selimsahin.userservice.producer;

import com.selimsahin.userservice.dto.AverageRatingUpdateDTO;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic.average-rating-update}")
    private String averageRatingUpdateTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

//    public void publishUserReviewCreatedEvent(UserReviewDetailDTO userReviewDetailDTO) {
//        kafkaTemplate.send(userReviewCreatedTopic, userReviewDetailDTO);
//    }

    public void publishAverageRatingCalculatedEvent(Long restaurantId, Double averageRating) {

        AverageRatingUpdateDTO averageRatingUpdateDTO = new AverageRatingUpdateDTO(restaurantId, averageRating);
        kafkaTemplate.send(averageRatingUpdateTopic, averageRatingUpdateDTO);
    }
}
