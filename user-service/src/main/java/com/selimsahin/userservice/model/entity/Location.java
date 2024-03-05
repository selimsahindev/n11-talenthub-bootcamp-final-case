package com.selimsahin.userservice.model.entity;

import com.selimsahin.userservice.model.dto.LocationDTO;
import jakarta.persistence.*;
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
