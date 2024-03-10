package com.selimsahin.logaggregationservice.service;

import com.selimsahin.logaggregationservice.entity.ErrorLog;

/**
 * @author selimsahindev
 */
public interface ErrorLogService {

    void createErrorLog(ErrorLog errorLog);
}
