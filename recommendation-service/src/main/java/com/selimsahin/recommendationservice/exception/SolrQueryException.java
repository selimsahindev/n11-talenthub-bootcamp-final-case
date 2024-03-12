package com.selimsahin.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author selimsahindev
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

public class SolrQueryException extends RuntimeException {

        public SolrQueryException(String message) {
            super(message);
        }
}
