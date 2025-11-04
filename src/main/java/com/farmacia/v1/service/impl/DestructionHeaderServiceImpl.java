package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.DestructionHeaderDTO;
import com.farmacia.v1.dto.InstitutionTypeDTO;
import com.farmacia.v1.dto.MethodDestructionDTO;
import com.farmacia.v1.dto.UserDTO;
import com.farmacia.v1.entity.DestructionHeaderEntity;
import com.farmacia.v1.entity.InstitutionTypeEntity;
import com.farmacia.v1.entity.MethodDestructionEntity;
import com.farmacia.v1.entity.UserEntity;
import com.farmacia.v1.repository.DestructionHeaderRepository;
import com.farmacia.v1.repository.GradeRepository;
import com.farmacia.v1.service.IDestructionHeaderService;
import com.farmacia.v1.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestructionHeaderServiceImpl implements IDestructionHeaderService {

    @Autowired
    private DestructionHeaderRepository repository;


    private DestructionHeaderDTO mapToDTO(DestructionHeaderEntity entity) {
        return DestructionHeaderDTO.builder()
                .id(entity.getId())
                .act_number(entity.getAct_number())
                .date_destruction(entity.getDate_destruction())
                .observation(entity.getObservation())
                .state(entity.getState())
                .methodDestruction(mapToMethodDestructionDTO(entity.getMethodDestruction()))
                .user(mapToUserDTO(entity.getUser()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private DestructionHeaderEntity mapToEntity(DestructionHeaderDTO dto) {
        return DestructionHeaderEntity.builder()
                .id(dto.getId())
                .act_number(dto.getAct_number())
                .date_destruction(dto.getDate_destruction())
                .observation(dto.getObservation())
                .state(dto.getState())
                .methodDestruction(mapToMethodDestructionEntity(dto.getMethodDestruction()))
                .user(mapToUserEntity(dto.getUser()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private MethodDestructionDTO mapToMethodDestructionDTO(MethodDestructionEntity entity) {
        return MethodDestructionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private MethodDestructionEntity mapToMethodDestructionEntity(MethodDestructionDTO dto) {
        return MethodDestructionEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private UserDTO mapToUserDTO(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .firstLastName(entity.getFirstLastName())
                .secondLastName(entity.getSecondLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .rut(entity.getRut())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private UserEntity mapToUserEntity(UserDTO dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .firstLastName(dto.getFirstLastName())
                .secondLastName(dto.getSecondLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .rut(dto.getRut())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }


    public DestructionHeaderDTO create(DestructionHeaderDTO dto) {
        DestructionHeaderEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public DestructionHeaderDTO update(Integer id, DestructionHeaderDTO dto) {
        DestructionHeaderEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setAct_number(dto.getAct_number());
        entity.setDate_destruction(dto.getDate_destruction());
        entity.setObservation(dto.getObservation());
        entity.setState(dto.getState());
        entity.setMethodDestruction(mapToMethodDestructionEntity(dto.getMethodDestruction()));
        entity.setUser(mapToUserEntity(dto.getUser()));
        return mapToDTO(repository.save(entity));
    }

    @Override
    public DestructionHeaderDTO getById(Integer id) {
        DestructionHeaderEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<DestructionHeaderDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<DestructionHeaderDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<DestructionHeaderDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }





    /*Listar communas activas*/
    public List<DestructionHeaderDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<DestructionHeaderDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<DestructionHeaderDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        DestructionHeaderEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
