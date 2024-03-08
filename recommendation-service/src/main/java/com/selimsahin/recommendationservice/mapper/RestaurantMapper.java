package com.selimsahin.recommendationservice.mapper;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantSearchResponse restaurantDocumentToSearchResponse(RestaurantDocument document);

    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.longitude", target = "longitude")
    RestaurantDocument restaurantDtoToDocument(RestaurantDTO dto);

    RestaurantDTO restaurantJsonToDto(String json);
}
