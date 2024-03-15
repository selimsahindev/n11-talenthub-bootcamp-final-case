package com.selimsahin.restaurantservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Builder
public record ErrorLogDTO (
    String service,
    LocalDateTime timestamp,
    Integer status,
    String error,
    String message,
    String stackTrace
) {
}
