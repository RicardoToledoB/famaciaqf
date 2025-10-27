package com.farmacia.v1.service;

import com.farmacia.v1.dto.MethodDestructionDTO;

import java.util.List;

public interface IMethodDestructionService {

    MethodDestructionDTO create(MethodDestructionDTO dto);
    MethodDestructionDTO update(Integer id, MethodDestructionDTO dto);
    MethodDestructionDTO getById(Integer id);
    List<MethodDestructionDTO> getAll();
    void delete(Integer id);
}
