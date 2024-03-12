package com.selimsahin.recommendationservice.mapper;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.Location;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.solr.core.geo.Point;

import java.util.List;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantSearchResponse restaurantDocumentToSearchResponse(RestaurantDocument document);

    @Mapping(source = "location", target = "location", qualifiedByName = "convertLocationToString")
    RestaurantDocument restaurantDtoToDocument(RestaurantDTO dto);

    @Named("convertLocationToString")
    default String convertLocationToString(Location location) {
        if (location == null) {
            return null;
        }
        return location.latitude() + "," + location.longitude();
    }
}
