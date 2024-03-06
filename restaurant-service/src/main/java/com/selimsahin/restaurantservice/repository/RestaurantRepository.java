package com.selimsahin.restaurantservice.repository;

import com.selimsahin.restaurantservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
