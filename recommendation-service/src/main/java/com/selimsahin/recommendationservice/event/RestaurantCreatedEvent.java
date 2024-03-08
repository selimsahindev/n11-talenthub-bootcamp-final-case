package com.selimsahin.recommendationservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author selimsahindev
 */
@AllArgsConstructor
@Getter
public class RestaurantCreatedEvent {

    private String restaurantJson;
}
