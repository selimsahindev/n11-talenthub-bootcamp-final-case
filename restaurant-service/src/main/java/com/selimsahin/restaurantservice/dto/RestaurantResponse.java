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
    private Double averageRating;
    private Location location;

    @Override
    public String toString() {
        return "RestaurantResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averateRating=" + averageRating +
                ", location=" + location +
                '}';
    }
}
