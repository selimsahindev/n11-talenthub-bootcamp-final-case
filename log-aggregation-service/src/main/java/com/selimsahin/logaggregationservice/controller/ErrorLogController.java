package com.selimsahin.logaggregationservice.controller;

import com.selimsahin.logaggregationservice.dto.ErrorLogDTO;
import com.selimsahin.logaggregationservice.service.ErrorLogService;
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
@RequestMapping("api/v1/error-logs")
@RequiredArgsConstructor
@Tag(name = "Error Log", description = "Provides access to error logs")
public class ErrorLogController {

    private final ErrorLogService errorLogService;

    @Operation(summary = "Get All Error Logs", description = "Retrieves a list of all error logs")
    @ApiResponse(
            responseCode = "200",
            description = "List of error logs retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))
    )
    @GetMapping
    public ResponseEntity<List<ErrorLogDTO>> getErrorLogs() {
        return ResponseEntity.ok(errorLogService.getAllErrorLogs());
    }
}
