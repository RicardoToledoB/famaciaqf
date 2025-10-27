package com.farmacia.v1.service;

import com.farmacia.v1.dto.DestinationDTO;

import java.util.List;

public interface IDestinationService {

    DestinationDTO create(DestinationDTO dto);
    DestinationDTO update(Integer id, DestinationDTO dto);
    DestinationDTO getById(Integer id);
    List<DestinationDTO> getAll();
    void delete(Integer id);
}
