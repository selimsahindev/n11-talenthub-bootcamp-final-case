package com.selimsahin.userservice.dto.response;

/**
 * @author selimsahindev
 */
public class ErrorResponse extends RestResponse<Object> {

    public ErrorResponse(String message) {
        super(message, null);
    }

    public static ErrorResponse create(String message) {
        return new ErrorResponse(message);
    }
}