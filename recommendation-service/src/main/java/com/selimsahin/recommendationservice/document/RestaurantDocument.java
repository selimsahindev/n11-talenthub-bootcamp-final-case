package com.selimsahin.recommendationservice.document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author selimsahindev
 */
@SolrDocument(collection = "restaurants")
@RequiredArgsConstructor
@Getter
@Setter
public class RestaurantDocument {
    
    @Id
    @Indexed(name = "id", type = "long")
    private Long id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "average_rating", type = "pdouble")
    private Float averageRating;

    @Indexed(name = "latitude", type = "pdouble")
    private Double latitude;

    @Indexed(name = "longitude", type = "pdouble")
    private Double longitude;

    @Override
    public String toString() {
        return "RestaurantDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageScore=" + averageRating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
