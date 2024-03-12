package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.Location;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.exception.SolrQueryException;
import com.selimsahin.recommendationservice.mapper.RestaurantMapper;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final SolrClient solrClient;

    @Override
    public List<RestaurantDocument> getAllRestaurants() {

        try {
            SolrQuery query = new SolrQuery("*:*"); // Empty query fetches all documents
            query.addField("id");
            query.addField("name");
            query.addField("average_rating");
            query.addField("location");

            QueryResponse response = solrClient.query("restaurants", query); // Specify the collection name

            SolrDocumentList results = response.getResults();

            return results.stream()
                    .map(RestaurantDocument::fromSolrDocument)
                    .toList();

        } catch (SolrServerException | IOException e) {
            // Handle exceptions appropriately
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
            List<RestaurantSearchResponse> restaurantSearchResponses = new ArrayList<>();

            for (SolrDocument doc : solrDocuments) {
                double[] location = getLatLong((String) doc.getFieldValue("location"));

                RestaurantSearchResponse responseDto = new RestaurantSearchResponse(
                        (String) doc.getFieldValue("id"),
                        (String) doc.getFieldValue("name"),
                        (Double) doc.getFieldValue("average_rating"),
                        new Location(location[0], location[1])
                );
                restaurantSearchResponses.add(responseDto);
            }

            return restaurantSearchResponses;

        } catch (Exception e) {
            throw new SolrQueryException("Failed to retrieve restaurants from Solr");
        }
    }

    @Override
    public void saveRestaurantDocument(RestaurantDTO restaurantDto) {

        RestaurantDocument restaurantDocument = restaurantMapper.restaurantDtoToDocument(restaurantDto);

        restaurantRepository.save(restaurantDocument);

        log.info("Restaurant saved to Solr: {}", restaurantDocument);
    }

    private double[] getLatLong(String latLong) {
        String[] latLongStr = latLong.split(",");
        double[] result = new double[2];
        result[0] = Double.parseDouble(latLongStr[0]);
        result[1] = Double.parseDouble(latLongStr[1]);
        return result;
    }
}