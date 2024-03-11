package com.selimsahin.userservice.dto;

/**
 * @author selimsahindev
 */
public record RestaurantInfoDTO(
        Long id,
        String name,
        Double averageRating,
        Location location
) {
}