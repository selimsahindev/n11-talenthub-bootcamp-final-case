package com.selimsahin.userservice.model.dto;

import com.selimsahin.userservice.model.entity.Location;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author selimsahindev
 */
@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "Name can not be empty.")
    @Length(max = 80, message = "Name can not be longer than 80 characters.")
    private String name;

    @NotBlank(message = "Surname can not be empty.")
    @Length(max = 80, message = "Surname can not be longer than 80 characters.")
    private String surname;

    @NotBlank(message = "Email can not be empty.")
    @Email(message = "Please provide a valid email address.")
    @Length(max = 100, message = "Email can not be longer than 100 characters.")
    private String email;

    @NotNull
    private Location location;
}
