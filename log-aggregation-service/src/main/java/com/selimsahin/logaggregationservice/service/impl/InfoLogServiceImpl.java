package com.selimsahin.logaggregationservice.service.impl;

import com.selimsahin.logaggregationservice.entity.InfoLog;
import com.selimsahin.logaggregationservice.repository.InfoLogRepository;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class InfoLogServiceImpl implements InfoLogService {

    private final InfoLogRepository infoLogRepository;

    @Override
    public void createInfoLog(InfoLog infoLog) {
        infoLogRepository.save(infoLog);
    }
}
