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

    private T data;
    private String message;
    private LocalDateTime responseDate;

    public RestResponse(String message, T data){
        this.message = message;
        this.data = data;
        this.responseDate = LocalDateTime.now();
    }

    public static <T> RestResponse<T> empty(){
        return new RestResponse<>("success", null);
    }

    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>("success", t);
    }

    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>("error", t);
    }
}