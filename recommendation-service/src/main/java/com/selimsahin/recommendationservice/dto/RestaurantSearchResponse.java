package com.selimsahin.recommendationservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class RestaurantSearchResponse {

    private Long id;
    private String name;
    private Double averageScore;
}
