package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.LocationDetailDTO;
import com.selimsahin.userservice.entity.Location;
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
    public LocationDetailDTO createLocation(LocationDetailDTO locationDetailDTO) {

        Location location = Location.mapLocationDetailDTOToLocation(locationDetailDTO);
        location = locationRepository.save(location);

        return Location.mapLocationToLocationDetailDTO(location);
    }
}
