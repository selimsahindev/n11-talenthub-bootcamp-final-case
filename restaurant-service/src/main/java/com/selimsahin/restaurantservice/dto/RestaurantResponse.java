package com.selimsahin.restaurantservice.dto;

/**
 * @author selimsahindev
 */
public record RestaurantResponse(
    Long id,
    String name,
    Double averageRating,
    Location location
) {
}
