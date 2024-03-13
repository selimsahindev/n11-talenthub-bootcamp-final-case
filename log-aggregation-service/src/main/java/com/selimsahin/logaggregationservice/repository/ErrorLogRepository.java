package com.selimsahin.logaggregationservice.repository;

import com.selimsahin.logaggregationservice.entity.ErrorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author selimsahindev
 */
public interface ErrorLogRepository extends MongoRepository<ErrorLog, String> {
}
