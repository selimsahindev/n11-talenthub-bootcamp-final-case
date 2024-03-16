package com.selimsahin.restaurantservice.dto.response;

import com.selimsahin.restaurantservice.dto.Location;

/**
 * @author selimsahindev
 */
public record RestaurantResponse(
    String id,
    String name,
    Double averageRating,
    Location location
) {
}
