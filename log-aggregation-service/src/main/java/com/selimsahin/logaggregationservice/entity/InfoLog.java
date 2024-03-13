package com.selimsahin.logaggregationservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Document(collection = "info_logs")
@Builder
@Getter
@Setter
public class InfoLog {

    @Id
    private String id;

    private LocalDateTime date;
    private String message;
    private String description;

    private LocalDateTime createdAt;
}
