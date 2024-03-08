package com.selimsahin.restaurantservice.entity;

import com.selimsahin.restaurantservice.dto.Location;
import com.selimsahin.restaurantservice.entity.common.Auditable;
import jakarta.persistence.*;
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

    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating = 0.0;

    @Embedded
    private Location location;
}
