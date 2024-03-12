package com.selimsahin.recommendationservice.service;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantService {

    List<RestaurantDocument> getAllRestaurants();

    List<RestaurantSearchResponse> getRestaurantsByLocationNear(RestaurantSearchRequest request);

    void saveRestaurantDocument(RestaurantDTO restaurant);
}
