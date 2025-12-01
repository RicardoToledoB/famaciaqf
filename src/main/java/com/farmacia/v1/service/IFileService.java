package com.farmacia.v1.service;


import com.farmacia.v1.dto.FileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFileService {
    FileDTO create(FileDTO dto);
    FileDTO update(Integer id, FileDTO dto);
    FileDTO getById(Integer id);
    List<FileDTO> getAll();
    void delete(Integer id);

    // extras alineados a tu estilo
    List<FileDTO> listAll();
    List<FileDTO> listActive();
    List<FileDTO> listDeleted();
    void restore(Integer id);

    Page<FileDTO> getAllPaginated(String name, Pageable pageable);
    Page<FileDTO> getAllPaginated(Pageable pageable);
    Page<FileDTO> getAllPaginatedByReception(Integer receptionId, Pageable pageable);
}
