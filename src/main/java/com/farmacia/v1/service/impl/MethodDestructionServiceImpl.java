package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.MethodDestructionDTO;
import com.farmacia.v1.entity.MethodDestructionEntity;
import com.farmacia.v1.repository.CommuneRepository;
import com.farmacia.v1.repository.MethodDestructionRepository;
import com.farmacia.v1.service.ICommuneService;
import com.farmacia.v1.service.IMethodDestructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MethodDestructionServiceImpl implements IMethodDestructionService {

    @Autowired
    private MethodDestructionRepository repository;


    private MethodDestructionDTO mapToDTO(MethodDestructionEntity entity) {
        return MethodDestructionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private MethodDestructionEntity mapToEntity(MethodDestructionDTO dto) {
        return MethodDestructionEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    public MethodDestructionDTO create(MethodDestructionDTO dto) {
        MethodDestructionEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public MethodDestructionDTO update(Integer id, MethodDestructionDTO dto) {
        MethodDestructionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setName(dto.getName());
        return mapToDTO(repository.save(entity));
    }

    @Override
    public MethodDestructionDTO getById(Integer id) {
        MethodDestructionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<MethodDestructionDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<MethodDestructionDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<MethodDestructionDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }





    /*Listar communas activas*/
    public List<MethodDestructionDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<MethodDestructionDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<MethodDestructionDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        MethodDestructionEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
