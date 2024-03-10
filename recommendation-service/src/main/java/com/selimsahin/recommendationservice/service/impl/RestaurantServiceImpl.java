package com.selimsahin.recommendationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.event.RestaurantCreatedEvent;
import com.selimsahin.recommendationservice.mapper.RestaurantMapper;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final ObjectMapper objectMapper;

    public List<RestaurantSearchResponse> getRestaurantsByLocation(RestaurantSearchRequest request) {

        List<RestaurantDocument> restaurants =
                restaurantRepository.findByLocation(request.getLatitude(), request.getLongitude());

        return restaurants.stream()
                .map(restaurantMapper::restaurantDocumentToSearchResponse)
                .toList();
    }

    public void saveRestaurantDocument(RestaurantDTO restaurantDto) {

        RestaurantDocument restaurantDocument = restaurantMapper.restaurantDtoToDocument(restaurantDto);

        restaurantRepository.save(restaurantDocument);

        log.info("Restaurant saved to Solr: {}", restaurantDocument);
    }

    @EventListener
    public void handleRestaurantCreatedEvent(RestaurantCreatedEvent event) throws JsonProcessingException {

        RestaurantDTO restaurantDto = objectMapper.readValue(event.restaurantJson(), RestaurantDTO.class);
        saveRestaurantDocument(restaurantDto);
    }
}