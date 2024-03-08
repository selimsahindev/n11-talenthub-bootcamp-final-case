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
@SolrDocument(collection = "restaurant")
@RequiredArgsConstructor
@Getter
@Setter
public class RestaurantDocument {
    
    @Id
    @Indexed(name = "id", type = "long")
    private Long id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "averageScore", type = "pdouble")
    private Float averageScore;

    @Indexed(name = "latitude", type = "pdouble")
    private Double latitude;

    @Indexed(name = "longitude", type = "pdouble")
    private Double longitude;
}
