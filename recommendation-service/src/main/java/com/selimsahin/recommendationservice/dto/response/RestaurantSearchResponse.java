package com.selimsahin.recommendationservice.dto.response;

import com.selimsahin.recommendationservice.dto.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@AllArgsConstructor
@Getter
@Setter
public class RestaurantSearchResponse {

    private String id;
    private String name;
    private Double averageRating;
    private Location location;
}
