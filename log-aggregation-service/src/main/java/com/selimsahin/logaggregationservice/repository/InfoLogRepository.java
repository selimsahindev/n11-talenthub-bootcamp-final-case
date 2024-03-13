package com.selimsahin.logaggregationservice.repository;

import com.selimsahin.logaggregationservice.entity.InfoLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author selimsahindev
 */
public interface InfoLogRepository extends MongoRepository<InfoLog, String> {
}
