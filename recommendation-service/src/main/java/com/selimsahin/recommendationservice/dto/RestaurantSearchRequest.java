package com.selimsahin.recommendationservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author selimsahindev
 */
@Builder
public record RestaurantSearchRequest (
    @NotBlank(message = "Location cannot be empty.")
    String location
) {
}
