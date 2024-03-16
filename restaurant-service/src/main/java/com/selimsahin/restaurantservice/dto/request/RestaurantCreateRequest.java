package com.selimsahin.restaurantservice.dto.request;

import com.selimsahin.restaurantservice.dto.Location;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @author selimsahindev
 */
public record RestaurantCreateRequest (

    @NotBlank(message = "Name is required.")
    @Length(max = 120, message = "Restaurant name can not be longer than 120 characters.")
    String name,

    @NotNull(message = "Location is required.")
    Location location
) {
}
