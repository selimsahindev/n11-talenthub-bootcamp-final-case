package com.selimsahin.logaggregationservice.controller;

import com.selimsahin.logaggregationservice.dto.InfoLogDTO;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("api/v1/info-logs")
@RequiredArgsConstructor
public class InfoLogController {

    private final InfoLogService infoLogService;

    @GetMapping
    public ResponseEntity<List<InfoLogDTO>> getInfoLogs() {
        return ResponseEntity.ok(infoLogService.getAllInfoLogs());
    }
}
