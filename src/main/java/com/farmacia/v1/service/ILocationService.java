package com.farmacia.v1.service;

import com.farmacia.v1.dto.LocationDTO;

import java.util.List;

public interface ILocationService {

    LocationDTO create(LocationDTO dto);
    LocationDTO update(Integer id, LocationDTO dto);
    LocationDTO getById(Integer id);
    List<LocationDTO> getAll();
    void delete(Integer id);
}
