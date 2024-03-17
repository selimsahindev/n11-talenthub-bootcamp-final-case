package com.selimsahin.logaggregationservice.controller;

import com.selimsahin.logaggregationservice.dto.InfoLogDTO;
import com.selimsahin.logaggregationservice.service.InfoLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Info Log", description = "Provides access to info logs")
public class InfoLogController {

    private final InfoLogService infoLogService;

    @Operation(summary = "Get All Info Logs", description = "Retrieves a list of all info logs")
    @ApiResponse(responseCode = "200", description = "List of info logs retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InfoLogDTO.class))
    )
    @GetMapping
    public ResponseEntity<List<InfoLogDTO>> getInfoLogs() {
        return ResponseEntity.ok(infoLogService.getAllInfoLogs());
    }
}
