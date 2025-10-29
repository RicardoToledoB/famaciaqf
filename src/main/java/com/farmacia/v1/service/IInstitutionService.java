package com.farmacia.v1.service;

import com.farmacia.v1.dto.InstitutionDTO;

import java.util.List;

public interface IInstitutionService {

    InstitutionDTO create(InstitutionDTO dto);
    InstitutionDTO update(Integer id, InstitutionDTO dto);
    InstitutionDTO getById(Integer id);
    List<InstitutionDTO> getAll();
    void delete(Integer id);
}
