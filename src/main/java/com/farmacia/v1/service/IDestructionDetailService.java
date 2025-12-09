package com.farmacia.v1.service;

import com.farmacia.v1.dto.DestructionDetailDTO;

import java.util.List;

public interface IDestructionDetailService {

    DestructionDetailDTO create(DestructionDetailDTO dto);
    DestructionDetailDTO update(Integer id, DestructionDetailDTO dto);
    DestructionDetailDTO getById(Integer id);
    List<DestructionDetailDTO> getAll();
    void delete(Integer id);
}
