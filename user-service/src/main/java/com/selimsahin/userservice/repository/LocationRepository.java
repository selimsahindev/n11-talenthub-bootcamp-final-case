package com.selimsahin.userservice.repository;

import com.selimsahin.userservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
