package com.selimsahin.recommendationservice.dto;

import javax.validation.constraints.NotNull;

/**
 * @author selimsahindev
 */
public record Location(

        @NotNull(message = "Latitude is required")
        Double latitude,

        @NotNull(message = "Longitude is required")
        Double longitude
) {
}