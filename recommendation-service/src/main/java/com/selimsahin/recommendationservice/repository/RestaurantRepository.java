package com.selimsahin.recommendationservice.repository;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
public interface RestaurantRepository extends SolrCrudRepository<RestaurantDocument, String> {

    @Query("latitude:?0 AND longitude:?1")
    List<RestaurantDocument> findByLocation(Double latitude, Double longitude);
}
