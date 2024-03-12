package com.selimsahin.logaggregationservice.repository;

import com.selimsahin.logaggregationservice.entity.InfoLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author selimsahindev
 */
public interface InfoLogRepository extends JpaRepository<InfoLog, Long> {
}
