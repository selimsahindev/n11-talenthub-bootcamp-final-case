package com.selimsahin.recommendationservice.exception.errormessages;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
public record GeneralErrorMessage(LocalDateTime date, String message, String description) {

}