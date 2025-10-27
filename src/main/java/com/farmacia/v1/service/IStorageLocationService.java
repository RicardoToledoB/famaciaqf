package com.farmacia.v1.service;

import com.farmacia.v1.dto.StorageLocationDTO;

import java.util.List;

public interface IStorageLocationService {

    StorageLocationDTO create(StorageLocationDTO dto);
    StorageLocationDTO update(Integer id, StorageLocationDTO dto);
    StorageLocationDTO getById(Integer id);
    List<StorageLocationDTO> getAll();
    void delete(Integer id);
}
