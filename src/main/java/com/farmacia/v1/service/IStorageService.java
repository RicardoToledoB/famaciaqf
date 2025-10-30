package com.farmacia.v1.service;

import com.farmacia.v1.dto.StorageDTO;

import java.util.List;

public interface IStorageService {

    StorageDTO create(StorageDTO dto);
    StorageDTO update(Integer id, StorageDTO dto);
    StorageDTO getById(Integer id);
    List<StorageDTO> getAll();
    void delete(Integer id);
}
