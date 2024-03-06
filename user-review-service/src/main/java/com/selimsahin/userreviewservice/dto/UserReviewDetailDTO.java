package com.selimsahin.userreviewservice.dto;

import com.selimsahin.userreviewservice.enums.UserRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class UserReviewDetailDTO {
    private Long id;
    private Long userId;
    private UserRating rate;
    private String comment;
}
