package com.farmacia.v1.service;

import com.farmacia.v1.dto.SubstanceTypeDTO;

import java.util.List;

public interface ISubstanceTypeService {

    SubstanceTypeDTO create(SubstanceTypeDTO dto);
    SubstanceTypeDTO update(Integer id, SubstanceTypeDTO dto);
    SubstanceTypeDTO getById(Integer id);
    List<SubstanceTypeDTO> getAll();
    void delete(Integer id);
}
