package com.selimsahin.restaurantservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class RestaurantResponse {

    private Long id;
    private String name;
    private Location location;
}
