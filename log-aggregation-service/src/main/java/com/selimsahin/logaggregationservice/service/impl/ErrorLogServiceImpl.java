package com.selimsahin.logaggregationservice.service.impl;

import com.selimsahin.logaggregationservice.entity.ErrorLog;
import com.selimsahin.logaggregationservice.repository.ErrorLogRepository;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    @Override
    public void createErrorLog(ErrorLog errorLog) {
        errorLogRepository.save(errorLog);
    }
}
