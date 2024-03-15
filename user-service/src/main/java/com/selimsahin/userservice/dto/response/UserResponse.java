package com.selimsahin.userservice.dto.response;

import com.selimsahin.userservice.dto.Location;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class UserResponse {

    private String name;
    private String surname;
    private String email;
    private Location location;
}
