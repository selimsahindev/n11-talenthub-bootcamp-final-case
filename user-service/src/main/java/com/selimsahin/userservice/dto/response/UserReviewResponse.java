package com.selimsahin.userservice.dto.response;

import com.selimsahin.userservice.enums.UserRating;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Builder
public record UserReviewResponse (
    Long id,
    Long userId,
    Long restaurantId,
    UserRating rate,
    String comment
) {
}
