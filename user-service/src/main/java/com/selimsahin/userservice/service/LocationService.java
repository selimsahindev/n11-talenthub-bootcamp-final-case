package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.LocationDetailDTO;

/**
 * @author selimsahindev
 */
public interface LocationService {

    LocationDetailDTO createLocation(LocationDetailDTO locationDetailDTO);
}
