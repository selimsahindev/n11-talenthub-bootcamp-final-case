package com.selimsahin.userservice.dto.request;

import com.selimsahin.userservice.dto.Location;
import com.selimsahin.userservice.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author selimsahindev
 */
@Builder
public record UserCreateRequest (

    @NotBlank(message = "Name is required.")
    @Length(max = 80, message = "Name can not be longer than 80 characters.")
    String name,

    @NotBlank(message = "Surname is required.")
    @Length(max = 80, message = "Surname can not be longer than 80 characters.")
    String surname,

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    @Length(max = 100, message = "Email can not be longer than 100 characters.")
    String email,

    @NotNull(message = "Location is required.")
    Location location,

    @NotNull(message = "Status is required.")
    @Enumerated(EnumType.STRING)
    UserStatus status
) {
}
