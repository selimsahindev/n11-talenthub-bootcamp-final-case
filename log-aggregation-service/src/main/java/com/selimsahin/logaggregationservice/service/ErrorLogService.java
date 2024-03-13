package com.selimsahin.logaggregationservice.service;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.entity.ErrorLog;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface ErrorLogService {

    List<ErrorLogDTO> getAllErrorLogs();

    void createErrorLog(ErrorLogDTO errorLog);
}
