package com.farmacia.v1.service;

import com.farmacia.v1.dto.TemplateDTO;

import java.util.List;

public interface ITemplateService {

    TemplateDTO create(TemplateDTO dto);
    TemplateDTO update(Integer id, TemplateDTO dto);
    TemplateDTO getById(Integer id);
    List<TemplateDTO> getAll();
    void delete(Integer id);
}
