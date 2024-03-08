package com.selimsahin.recommendationservice.repository;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantRepository extends SolrCrudRepository<RestaurantDocument, Long> {

    @Query("latitude:?0 AND longitude:?1")
    List<RestaurantDocument> findByLocation(Double latitude, Double longitude);
}
