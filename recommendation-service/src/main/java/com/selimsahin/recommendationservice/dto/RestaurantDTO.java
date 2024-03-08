package com.selimsahin.recommendationservice.dto;

/**
 * @author selimsahindev
 */
public record RestaurantCreatedEventDTO(

    Long id,
    String name,
    Location location
) {
}

