package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.event.RestaurantCreatedEvent;
import com.selimsahin.recommendationservice.mapper.RestaurantMapper;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantSearchResponse> getRestaurantsByLocation(RestaurantSearchRequest request) {

        List<RestaurantDocument> restaurants =
                restaurantRepository.findByLocation(request.getLatitude(), request.getLongitude());

        return restaurants.stream()
                .map(restaurantMapper::restaurantDocumentToSearchResponse)
                .toList();
    }

    public void saveRestaurantDocument(RestaurantDTO dto) {

        RestaurantDocument document = restaurantMapper.restaurantDtoToDocument(dto);

        System.out.println("Saving restaurant document: " + document);
        restaurantRepository.save(document);
    }

    @EventListener
    public void handleRestaurantCreatedEvent(RestaurantCreatedEvent event) {

            RestaurantDTO restaurantDto = restaurantMapper.restaurantJsonToDto(event.restaurantJson());

            //saveRestaurantDocument(restaurantDto);

            System.out.println("RestaurantCreatedEvent: " + event.restaurantJson());
    }
}