package com.selimsahin.recommendationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@RequiredArgsConstructor
@Getter
@Setter
public class RestaurantDTO {

    private Long id;
    private String name;
    private Double averageRating;
    private Location location;
}

