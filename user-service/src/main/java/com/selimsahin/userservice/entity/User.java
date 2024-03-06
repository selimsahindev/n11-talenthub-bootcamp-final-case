package com.selimsahin.userservice.entity;

import com.selimsahin.userservice.enums.Gender;
import com.selimsahin.userservice.enums.UserStatus;
import com.selimsahin.userservice.entity.common.Auditable;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        User user = (User) obj;

        if (!id.equals(user.id)) { return false; }
        if (!name.equals(user.name)) { return false; }
        if (!surname.equals(user.surname)) { return false; }
        if (!email.equals(user.email)) { return false; }
        if (!gender.equals(user.gender)) { return false; }
        if (!location.equals(user.location)) { return false; }
        return status == user.status;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
