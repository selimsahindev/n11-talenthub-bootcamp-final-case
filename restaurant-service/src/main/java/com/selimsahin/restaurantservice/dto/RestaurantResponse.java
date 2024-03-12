package com.selimsahin.restaurantservice.dto;

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
