package com.selimsahin.recommendationservice.document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.*;

/**
 * @author selimsahindev
 */
@SolrDocument(collection = "restaurants")
@RequiredArgsConstructor
@Getter
@Setter
public class RestaurantDocument {
    
    @Id
    @Field("id")
    private String id;

    @Field("name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Field("average_rating")
    @NotNull(message = "Average rating is mandatory")
    @PositiveOrZero(message = "Average rating must be a positive value or zero")
    @DecimalMax(value = "5.0", inclusive = true, message = "Average rating must be less than or equal to 5")
    private Double averageRating;

    @Field("location")
    @NotBlank(message = "Location is mandatory")
    private String location;

    public static RestaurantDocument fromSolrDocument(org.apache.solr.common.SolrDocument document) {
        RestaurantDocument restaurant = new RestaurantDocument();
        restaurant.setId((String) document.getFieldValue("id"));
        restaurant.setName((String) document.getFieldValue("name"));
        restaurant.setAverageRating((Double) document.getFieldValue("average_rating"));
        restaurant.setLocation((String) document.getFieldValue("location"));
        return restaurant;
    }
}
