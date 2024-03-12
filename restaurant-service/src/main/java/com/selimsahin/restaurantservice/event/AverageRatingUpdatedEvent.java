package com.selimsahin.restaurantservice.event;

import com.selimsahin.restaurantservice.dto.AverageRatingUpdateDTO;

/**
 * @author selimsahindev
 */
public record AverageRatingUpdatedEvent(String payload) {
}
