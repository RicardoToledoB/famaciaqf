package com.farmacia.v1.service;

import com.farmacia.v1.dto.GradeDTO;

import java.util.List;

public interface IGradeService {

    GradeDTO create(GradeDTO dto);
    GradeDTO update(Integer id, GradeDTO dto);
    GradeDTO getById(Integer id);
    List<GradeDTO> getAll();
    void delete(Integer id);
}
