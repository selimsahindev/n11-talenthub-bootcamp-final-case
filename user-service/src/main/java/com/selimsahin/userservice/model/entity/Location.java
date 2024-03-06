package com.selimsahin.userservice.model.entity;

import com.selimsahin.userservice.model.dto.LocationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Location {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "User is required.")
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;

        @NotNull(message = "Latitude is required.")
        @Column(name = "latitude", nullable = false)
        private Double latitude;

        @NotNull(message = "Longitude is required.")
        @Column(name = "longitude", nullable = false)
        private Double longitude;

        public Location(Double latitude, Double longitude) {
                this.latitude = latitude;
                this.longitude = longitude;
        }

        // TODO: Implement MapStruct for mapping
        public static Location mapLocationDTOToLocation(LocationDTO locationDTO) {
                return new Location(locationDTO.latitude(), locationDTO.longitude());
        }
        public static LocationDTO mapLocationToLocationDTO(Location location) {
                return new LocationDTO(location.getLatitude(), location.getLongitude());
        }
}
