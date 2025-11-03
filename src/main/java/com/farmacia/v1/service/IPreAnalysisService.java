package com.farmacia.v1.service;

import com.farmacia.v1.dto.PreAnalysisDTO;

import java.util.List;

public interface IPreAnalysisService {

    PreAnalysisDTO create(PreAnalysisDTO dto);
    PreAnalysisDTO update(Integer id, PreAnalysisDTO dto);
    PreAnalysisDTO getById(Integer id);
    List<PreAnalysisDTO> getAll();
    void delete(Integer id);
}
