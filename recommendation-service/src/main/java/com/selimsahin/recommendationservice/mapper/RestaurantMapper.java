package com.selimsahin.recommendationservice.mapper;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.Location;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "location", target = "location", qualifiedByName = "convertStringToLocation")
    RestaurantSearchResponse mapToRestaurantSearchResponse(RestaurantDocument document);

    @Mapping(source = "location", target = "location", qualifiedByName = "convertLocationToString")
    RestaurantDocument mapToRestaurantDocument(RestaurantDTO dto);

    @Named("convertLocationToString")
    default String convertLocationToString(Location location) {
        if (location == null) {
            return null;
        }
        return location.latitude() + "," + location.longitude();
    }

    @Named("convertStringToLocation")
    default Location convertStringToLocation(String location) {
        if (location == null || location.isBlank() || location.isEmpty()) {
            return null;
        }
        String[] latLong = location.split(",");
        return new Location(
                Double.parseDouble(latLong[0]),
                Double.parseDouble(latLong[1])
        );
    }
}
