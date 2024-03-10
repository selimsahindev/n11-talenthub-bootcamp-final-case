package com.selimsahin.recommendationservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author selimsahindev
 */
public record RestaurantCreatedEvent (String restaurantJson) {
}
