package com.farmacia.v1.service;

import com.farmacia.v1.dto.PackagingDTO;

import java.util.List;

public interface IPackagingService {

    PackagingDTO create(PackagingDTO dto);
    PackagingDTO update(Integer id, PackagingDTO dto);
    PackagingDTO getById(Integer id);
    List<PackagingDTO> getAll();
    void delete(Integer id);
}
