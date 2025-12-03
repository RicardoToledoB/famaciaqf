package com.farmacia.v1.service;

import com.farmacia.v1.dto.ReservedDTO;

import java.util.List;

public interface IReservedService {

    ReservedDTO create(ReservedDTO dto);
    ReservedDTO update(Integer id, ReservedDTO dto);
    ReservedDTO getById(Integer id);
    List<ReservedDTO> getAll();
    void delete(Integer id);
}
