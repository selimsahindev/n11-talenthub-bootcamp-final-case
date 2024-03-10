package com.selimsahin.logaggregationservice.repository;

import com.selimsahin.logaggregationservice.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
