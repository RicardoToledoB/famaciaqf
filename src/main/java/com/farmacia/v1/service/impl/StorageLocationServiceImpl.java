package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.StorageLocationDTO;
import com.farmacia.v1.entity.StorageLocationEntity;
import com.farmacia.v1.repository.CommuneRepository;
import com.farmacia.v1.repository.StorageLocationRepository;
import com.farmacia.v1.service.ICommuneService;
import com.farmacia.v1.service.IStorageLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageLocationServiceImpl implements IStorageLocationService {

    @Autowired
    private StorageLocationRepository repository;


    private StorageLocationDTO mapToDTO(StorageLocationEntity entity) {
        return StorageLocationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private StorageLocationEntity mapToEntity(StorageLocationDTO dto) {
        return StorageLocationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    public StorageLocationDTO create(StorageLocationDTO dto) {
        StorageLocationEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public StorageLocationDTO update(Integer id, StorageLocationDTO dto) {
        StorageLocationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setName(dto.getName());
        return mapToDTO(repository.save(entity));
    }

    @Override
    public StorageLocationDTO getById(Integer id) {
        StorageLocationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<StorageLocationDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<StorageLocationDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<StorageLocationDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }





    /*Listar communas activas*/
    public List<StorageLocationDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<StorageLocationDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<StorageLocationDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        StorageLocationEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
