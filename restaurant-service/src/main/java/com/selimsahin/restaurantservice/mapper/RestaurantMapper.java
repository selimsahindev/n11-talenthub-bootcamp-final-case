package com.selimsahin.restaurantservice.mapper;

import com.selimsahin.restaurantservice.dto.request.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.response.RestaurantResponse;
import com.selimsahin.restaurantservice.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    Restaurant mapCreateRequestToRestaurant(RestaurantCreateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "averageRating", source = "averageRating")
    @Mapping(target = "location", source = "location")
    RestaurantResponse mapRestaurantToRestaurantResponse(Restaurant restaurant);
}
