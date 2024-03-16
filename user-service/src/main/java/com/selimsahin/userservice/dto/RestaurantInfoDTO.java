package com.selimsahin.userservice.dto;

import lombok.Builder;

/**
 * @author selimsahindev
 */
@Builder
public record RestaurantInfoDTO(
        Long id,
        String name,
        Double averageRating,
        Location location
) {
}