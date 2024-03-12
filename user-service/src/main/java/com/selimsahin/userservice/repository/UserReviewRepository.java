package com.selimsahin.userservice.repository;

import com.selimsahin.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    List<UserReview> findAllByUserId(Long userId);

    List<UserReview> findAllByRestaurantId(Long restaurantId);

    @Query("SELECT AVG(cast(ur.rate as integer)) as average_rate FROM UserReview ur WHERE ur.restaurantId = :restaurantId")
    Double findAverageRatingByRestaurantId(Long restaurantId);
}
