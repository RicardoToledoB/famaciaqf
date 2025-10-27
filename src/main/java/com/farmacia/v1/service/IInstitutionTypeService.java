package com.farmacia.v1.service;

import com.farmacia.v1.dto.InstitutionTypeDTO;

import java.util.List;

public interface IInstitutionTypeService {

    InstitutionTypeDTO create(InstitutionTypeDTO dto);
    InstitutionTypeDTO update(Integer id, InstitutionTypeDTO dto);
    InstitutionTypeDTO getById(Integer id);
    List<InstitutionTypeDTO> getAll();
    void delete(Integer id);
}
