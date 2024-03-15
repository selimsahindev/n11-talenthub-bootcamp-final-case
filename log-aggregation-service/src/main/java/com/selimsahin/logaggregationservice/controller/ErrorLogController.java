package com.selimsahin.logaggregationservice.controller;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("api/v1/error-logs")
@RequiredArgsConstructor
public class ErrorLogController {

    private final ErrorLogService errorLogService;

    @GetMapping
    public ResponseEntity<List<ErrorLogDTO>> getErrorLogs() {
        return ResponseEntity.ok(errorLogService.getAllErrorLogs());
    }
}
