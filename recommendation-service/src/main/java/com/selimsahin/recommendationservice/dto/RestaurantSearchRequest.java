package com.selimsahin.recommendationservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class RestaurantSearchRequest {

    @NotNull(message = "Latitude cannot be null.")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null.")
    private Double longitude;
}
