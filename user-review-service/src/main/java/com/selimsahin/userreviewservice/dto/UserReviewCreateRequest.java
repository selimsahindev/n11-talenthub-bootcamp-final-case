package com.selimsahin.userreviewservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @author selimsahindev
 */
public record UserReviewCreateRequest (

        @NotNull(message = "User ID is required.")
        Long userId,

        @NotNull(message = "Rating is required.")
        @Range(min = 1, max = 5, message = "Rating must be between 1 and 5.")
        Integer rate,

        @NotBlank(message = "Comment is required.")
        @Length(max = 255, message = "Comment can not be longer than 255 characters.")
        String comment
) {
}
