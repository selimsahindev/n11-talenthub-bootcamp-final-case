package com.selimsahin.userservice.model.entity;

import com.selimsahin.userservice.model.entity.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "surname", nullable = false, length = 80)
    private String surname;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    private Location location;
}
