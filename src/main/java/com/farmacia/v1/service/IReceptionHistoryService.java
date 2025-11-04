package com.farmacia.v1.service;

import com.farmacia.v1.dto.ReceptionHistoryDTO;

import java.util.List;

public interface IReceptionHistoryService {

    ReceptionHistoryDTO create(ReceptionHistoryDTO dto);
    ReceptionHistoryDTO update(Integer id, ReceptionHistoryDTO dto);
    ReceptionHistoryDTO getById(Integer id);
    List<ReceptionHistoryDTO> getAll();
    void delete(Integer id);
}
