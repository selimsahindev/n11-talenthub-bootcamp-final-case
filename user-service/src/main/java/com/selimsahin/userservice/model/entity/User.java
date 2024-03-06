package com.selimsahin.userservice.model.entity;

import com.selimsahin.userservice.enums.Gender;
import com.selimsahin.userservice.enums.UserStatus;
import com.selimsahin.userservice.model.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @NotNull(message = "Surname is required.")
    @Column(name = "surname", length = 80, nullable = false)
    private String surname;

    @NotBlank(message = "Email is required.")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotNull(message = "Gender is required.")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 30, nullable = false)
    private Gender gender;

    @NotBlank(message = "Location is required.")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;
}
