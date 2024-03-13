package com.selimsahin.logaggregationservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Document(collection = "error_logs")
@Builder
@Getter
@Setter
public class ErrorLog {

    @Id
    private String id;

    private LocalDateTime date;
    private String message;
    private String description;
}
