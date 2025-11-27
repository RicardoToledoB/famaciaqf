package com.farmacia.v1.service;

import com.farmacia.v1.dto.MicroAnalysisDTO;

import java.util.List;

public interface IMicroAnalysisService {

    MicroAnalysisDTO create(MicroAnalysisDTO dto);
    MicroAnalysisDTO update(Integer id, MicroAnalysisDTO dto);
    MicroAnalysisDTO getById(Integer id);
    List<MicroAnalysisDTO> getAll();
    void delete(Integer id);
}
