package com.selimsahin.userreviewservice.repository;

import com.selimsahin.userreviewservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
