package com.selimsahin.userreviewservice.enums;

import lombok.Getter;

/**
 * @author selimsahindev
 */
@Getter
public enum UserRating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    UserRating(int value) {
        this.value = value;
    }

    public static UserRating fromValue(int value) {
        for (UserRating rating : UserRating.values()) {
            if (rating.getValue() == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid UserRating value: " + value);
    }
}
