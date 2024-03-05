package com.selimsahin.userservice.model.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author selimsahindev
 */
public record LocationDTO(
        @NotNull
        Double latitude,

        @NotNull
        Double longitude) {
}
