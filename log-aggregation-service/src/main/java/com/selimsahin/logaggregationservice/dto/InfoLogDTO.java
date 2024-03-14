package com.selimsahin.logaggregationservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Builder
public record InfoLogDTO(

        String id,
        LocalDateTime date,
        String message,
        String description
) {
}