package com.selimsahin.restaurantservice.entity;

import com.selimsahin.restaurantservice.dto.Location;
import com.selimsahin.restaurantservice.entity.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 120, message = "Name must be maximum 120 characters")
    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating = 0.0;

    @Embedded
    private Location location;
}
