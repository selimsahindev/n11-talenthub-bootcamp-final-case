package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.request.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.response.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.exception.SolrQueryException;
import com.selimsahin.recommendationservice.mapper.RestaurantMapper;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import com.selimsahin.recommendationservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final SolrClient solrClient;
    private final AppLogger appLogger;

    @Override
    public List<RestaurantSearchResponse> getAllRestaurants() {

        try {
            SolrQuery query = new SolrQuery("*:*"); // Empty query fetches all documents
            query.addField("id");
            query.addField("name");
            query.addField("average_rating");
            query.addField("location");

            QueryResponse response = solrClient.query("restaurants", query); // Specify the collection name
            SolrDocumentList solrDocuments = response.getResults();

            return getRestaurantSearchResponses(solrDocuments);

        } catch (Exception e) {
            throw new SolrQueryException("Failed to retrieve restaurants from Solr");
        }
    }

    @Override
    public List<RestaurantSearchResponse> getRestaurantsByLocationNear(RestaurantSearchRequest request) {

        try {
            double[] latLong = getLatLong(request.location());
            double latitude = latLong[0];
            double longitude = latLong[1];

            SolrQuery query = new SolrQuery("*:*");
            query.set("fq", "{!geofilt pt=" + latitude + "," + longitude + " sfield=location d=10}");
            query.set("indent", true);
            query.set("q.op", "OR");
            query.set("rows", 3);
            query.set("sort", "sum(mul(average_rating,14),mul(sub(10,geodist(" + latitude + "," + longitude + ",location)),3)) desc");

            QueryResponse queryResponse = solrClient.query("restaurants", query);
            SolrDocumentList solrDocuments = queryResponse.getResults();

            return getRestaurantSearchResponses(solrDocuments);

        } catch (Exception e) {
            throw new SolrQueryException("Failed to retrieve restaurants from Solr");
        }
    }

    @Override
    public void saveRestaurantDocument(RestaurantDTO restaurantDto) {

        RestaurantDocument restaurantDocument = restaurantMapper.mapToRestaurantDocument(restaurantDto);
        restaurantRepository.save(restaurantDocument);

        appLogger.logInfo("Restaurant saved to Solr", "Restaurant saved to Solr: " + restaurantDocument);
    }

    // Helper method to parse latitude and longitude from the request
    private double[] getLatLong(String latLong) {

        String[] latLongStr = latLong.split(",");

        return new double[] {
            Double.parseDouble(latLongStr[0]),
            Double.parseDouble(latLongStr[1])
        };
    }

    // Helper method to map Solr documents to RestaurantSearchResponse
    private List<RestaurantSearchResponse> getRestaurantSearchResponses(SolrDocumentList solrDocuments) {

        List<RestaurantSearchResponse> restaurantSearchResponses = new ArrayList<>();

        for (SolrDocument doc : solrDocuments) {
            RestaurantDocument restaurantDocument = RestaurantDocument.fromSolrDocument(doc);
            restaurantSearchResponses.add(restaurantMapper.mapToRestaurantSearchResponse(restaurantDocument));
        }
        return restaurantSearchResponses;
    }
}