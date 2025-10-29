package com.farmacia.v1.service;

import com.farmacia.v1.dto.PoliceDTO;

import java.util.List;

public interface IPoliceService {

    PoliceDTO create(PoliceDTO dto);
    PoliceDTO update(Integer id, PoliceDTO dto);
    PoliceDTO getById(Integer id);
    List<PoliceDTO> getAll();
    void delete(Integer id);
}
