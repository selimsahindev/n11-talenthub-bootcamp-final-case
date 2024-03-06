package com.selimsahin.userservice.repository;

import com.selimsahin.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
