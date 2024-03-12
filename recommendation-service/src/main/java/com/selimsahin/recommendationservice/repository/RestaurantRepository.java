package com.selimsahin.recommendationservice.repository;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author selimsahindev
 */
public interface RestaurantRepository extends SolrCrudRepository<RestaurantDocument, String> {
}
