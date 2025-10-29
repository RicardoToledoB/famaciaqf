package com.farmacia.v1.service;

import com.farmacia.v1.dto.ReceptionDTO;

import java.util.List;

public interface IReceptionService {

    ReceptionDTO create(ReceptionDTO dto);
    ReceptionDTO update(Integer id, ReceptionDTO dto);
    ReceptionDTO getById(Integer id);
    List<ReceptionDTO> getAll();
    void delete(Integer id);
}
