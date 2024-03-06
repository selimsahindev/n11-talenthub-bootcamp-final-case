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

    @Embedded
    private Location location;
}
