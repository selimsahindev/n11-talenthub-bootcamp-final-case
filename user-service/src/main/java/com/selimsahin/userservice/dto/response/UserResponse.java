package com.selimsahin.userservice.dto.response;

import com.selimsahin.userservice.dto.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Builder
public record UserResponse (
    Long id,
    String name,
    String surname,
    String email,
    Location location
) {
}
