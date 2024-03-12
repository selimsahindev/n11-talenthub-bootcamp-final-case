package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.mapper.RestaurantMapper;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

            QueryResponse response = solrClient.query("restaurants", query); // Specify the collection name

            SolrDocumentList results = response.getResults();

            System.out.println("Number of restaurants found: " + results.getNumFound());

            return results.stream()
                    .map(RestaurantDocument::fromSolrDocument)
                    .toList();

        } catch (SolrServerException | IOException e) {
            // Handle exceptions appropriately
            throw new RuntimeException("Failed to retrieve restaurants from Solr", e);
        }
    }

    @Override
    public List<RestaurantSearchResponse> getRestaurantsByLocationNear(RestaurantSearchRequest request) {
        return null;
    }

    @Override
    public void saveRestaurantDocument(RestaurantDTO restaurantDto) {

        RestaurantDocument restaurantDocument = restaurantMapper.restaurantDtoToDocument(restaurantDto);

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", restaurantDocument.getId());
        doc.addField("name", restaurantDocument.getName());
        doc.addField("average_rating", restaurantDocument.getAverageRating());
        doc.addField("location", restaurantDocument.getLocation());

        restaurantRepository.save(restaurantDocument);

        log.info("Restaurant saved to Solr: {}", restaurantDocument);
    }
}