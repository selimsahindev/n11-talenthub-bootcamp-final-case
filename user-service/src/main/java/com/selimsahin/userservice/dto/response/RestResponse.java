package com.selimsahin.userservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {

    public static final String SUCCESS_MESSAGE = "success";
    public static final String ERROR_MESSAGE = "error";

    private T data;
    private String message;
    private LocalDateTime responseDate;

    public RestResponse(String message, T data){
        this.message = message;
        this.data = data;
        this.responseDate = LocalDateTime.now();
    }

    public boolean isSuccess(){
        return message.equals(SUCCESS_MESSAGE);
    }

    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(SUCCESS_MESSAGE, null);
    }

    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>(SUCCESS_MESSAGE, t);
    }

    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(ERROR_MESSAGE, t);
    }
}