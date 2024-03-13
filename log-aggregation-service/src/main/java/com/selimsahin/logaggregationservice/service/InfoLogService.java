package com.selimsahin.logaggregationservice.service;

import com.selimsahin.logaggregationservice.dto.InfoLogDTO;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface InfoLogService {

    List<InfoLogDTO> getAllInfoLogs();

    void createInfoLog(InfoLogDTO infoLog);
}
