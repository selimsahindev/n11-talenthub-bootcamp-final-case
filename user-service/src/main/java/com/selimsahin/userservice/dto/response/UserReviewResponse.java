package com.selimsahin.userservice.dto.response;

import com.selimsahin.userservice.enums.UserRating;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class UserReviewResponse {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private UserRating rate;
    private String comment;
}
