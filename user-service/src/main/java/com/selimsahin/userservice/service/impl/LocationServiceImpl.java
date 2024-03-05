package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.model.dto.LocationDTO;
import com.selimsahin.userservice.model.entity.Location;
import com.selimsahin.userservice.repository.LocationRepository;
import com.selimsahin.userservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {

        Location location = Location.mapLocationDTOToLocation(locationDTO);
        location = locationRepository.save(location);

        return Location.mapLocationToLocationDTO(location);
    }
}
