package com.selimsahin.userservice.repository;

import com.selimsahin.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    Optional<List<UserReview>> findAllByUserId(Long userId);
}
