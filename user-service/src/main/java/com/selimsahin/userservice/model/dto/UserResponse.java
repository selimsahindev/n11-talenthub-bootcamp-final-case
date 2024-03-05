package com.selimsahin.userservice.model.dto;

import com.selimsahin.userservice.model.entity.Location;
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
