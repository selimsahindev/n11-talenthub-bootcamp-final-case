package com.selimsahin.logaggregationservice.service.impl;

import com.selimsahin.logaggregationservice.dto.InfoLogDTO;
import com.selimsahin.logaggregationservice.entity.InfoLog;
import com.selimsahin.logaggregationservice.repository.InfoLogRepository;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class InfoLogServiceImpl implements InfoLogService {

    private final InfoLogRepository infoLogRepository;

    @Override
    public void createInfoLog(InfoLogDTO errorLogDto) {
        InfoLog errorLog = InfoLog.builder()
                .date(errorLogDto.date())
                .message(errorLogDto.message())
                .description(errorLogDto.description())
                .build();

        infoLogRepository.save(errorLog);
    }

    @Override
    public List<InfoLogDTO> getAllInfoLogs() {
        return infoLogRepository.findAll().stream()
                .map(errorLog -> InfoLogDTO.builder()
                        .id(errorLog.getId())
                        .date(errorLog.getDate())
                        .message(errorLog.getMessage())
                        .description(errorLog.getDescription())
                        .build())
                .toList();
    }
}
