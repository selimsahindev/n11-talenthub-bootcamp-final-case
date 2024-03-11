package com.selimsahin.userservice.enums;

import lombok.Getter;

/**
 * @author selimsahindev
 */
@Getter
public enum UserRating {
    // This is not a valid rating, but we keep this to
    // solve the index problem in the PostgreSQL table.
    ZERO(0),
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
