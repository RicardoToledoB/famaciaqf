package com.farmacia.v1.service;

import com.farmacia.v1.dto.DestructionHeaderDTO;

import java.util.List;

public interface IDestructionHeaderService {

    DestructionHeaderDTO create(DestructionHeaderDTO dto);
    DestructionHeaderDTO update(Integer id, DestructionHeaderDTO dto);
    DestructionHeaderDTO getById(Integer id);
    List<DestructionHeaderDTO> getAll();
    void delete(Integer id);
}
