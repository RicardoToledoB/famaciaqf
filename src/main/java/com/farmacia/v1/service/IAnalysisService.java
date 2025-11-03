package com.farmacia.v1.service;

import com.farmacia.v1.dto.AnalysisDTO;

import java.util.List;

public interface IAnalysisService {

    AnalysisDTO create(AnalysisDTO dto);
    AnalysisDTO update(Integer id, AnalysisDTO dto);
    AnalysisDTO getById(Integer id);
    List<AnalysisDTO> getAll();
    void delete(Integer id);
}
