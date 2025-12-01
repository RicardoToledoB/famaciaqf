package com.farmacia.v1.service;

import com.farmacia.v1.dto.ChemicalTestDTO;

import java.util.List;

public interface IChemicalTestService {

    ChemicalTestDTO create(ChemicalTestDTO dto);
    ChemicalTestDTO update(Integer id, ChemicalTestDTO dto);
    ChemicalTestDTO getById(Integer id);
    List<ChemicalTestDTO> getAll();
    void delete(Integer id);
}
