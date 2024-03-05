package com.selimsahin.userservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;

        @Column(name = "latitude", nullable = false)
        private Double latitude;

        @Column(name = "longitude", nullable = false)
        private Double longitude;
}
