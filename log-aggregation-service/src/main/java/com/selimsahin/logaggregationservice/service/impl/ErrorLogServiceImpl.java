package com.selimsahin.logaggregationservice.service.impl;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.entity.ErrorLog;
import com.selimsahin.logaggregationservice.repository.ErrorLogRepository;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    @Override
    public void createErrorLog(ErrorLogDTO errorLogDto) {
        ErrorLog errorLog = ErrorLog.builder()
                .date(errorLogDto.date())
                .message(errorLogDto.message())
                .description(errorLogDto.description())
                .build();

        errorLogRepository.save(errorLog);
    }

    @Override
    public List<ErrorLogDTO> getAllErrorLogs() {
        return errorLogRepository.findAll().stream()
                .map(errorLog -> ErrorLogDTO.builder()
                        .id(errorLog.getId())
                        .date(errorLog.getDate())
                        .message(errorLog.getMessage())
                        .description(errorLog.getDescription())
                        .build())
                .toList();
    }
}
