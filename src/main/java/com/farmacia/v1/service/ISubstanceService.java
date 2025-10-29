package com.farmacia.v1.service;

import com.farmacia.v1.dto.SubstanceDTO;

import java.util.List;

public interface ISubstanceService {

    SubstanceDTO create(SubstanceDTO dto);
    SubstanceDTO update(Integer id, SubstanceDTO dto);
    SubstanceDTO getById(Integer id);
    List<SubstanceDTO> getAll();
    void delete(Integer id);
}
