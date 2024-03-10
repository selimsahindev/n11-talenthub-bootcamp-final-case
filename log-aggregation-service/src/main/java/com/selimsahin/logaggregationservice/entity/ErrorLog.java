package com.selimsahin.logaggregationservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "error_logs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String message;
    private String description;
}
