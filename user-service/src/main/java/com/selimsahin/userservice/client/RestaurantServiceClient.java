package com.selimsahin.userservice.client;

import com.selimsahin.userservice.dto.RestaurantInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author selimsahindev
 */
@FeignClient("restaurant-service/api/v1/restaurants")
public interface RestaurantServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<RestaurantInfoDTO> getRestaurantById(@PathVariable Long id);
}
