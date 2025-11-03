package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.CommuneDTO;
import com.farmacia.v1.dto.InstitutionDTO;
import com.farmacia.v1.dto.InstitutionTypeDTO;
import com.farmacia.v1.entity.CommuneEntity;
import com.farmacia.v1.entity.InstitutionEntity;
import com.farmacia.v1.entity.InstitutionTypeEntity;
import com.farmacia.v1.repository.CommuneRepository;
import com.farmacia.v1.repository.InstitutionTypeRepository;
import com.farmacia.v1.service.ICommuneService;
import com.farmacia.v1.service.IInstitutionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionTypeServiceImpl implements IInstitutionTypeService {

    @Autowired
    private InstitutionTypeRepository repository;


    private InstitutionTypeDTO mapToDTO(InstitutionTypeEntity entity) {
        return InstitutionTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .commune(mapToCommuneDTO(entity.getCommune()))
                .institution(mapToInstitutionDTO(entity.getInstitution()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private InstitutionTypeEntity mapToEntity(InstitutionTypeDTO dto) {
        return InstitutionTypeEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .commune(mapToCommuneEntity(dto.getCommune()))
                .institution(mapToInstitutionEntity(dto.getInstitution()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }



    private CommuneDTO mapToCommuneDTO(CommuneEntity entity) {
        return CommuneDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private CommuneEntity mapToCommuneEntity(CommuneDTO dto) {
        return CommuneEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }


    private InstitutionDTO mapToInstitutionDTO(InstitutionEntity entity) {
        return InstitutionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private InstitutionEntity mapToInstitutionEntity(InstitutionDTO dto) {
        return InstitutionEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    public InstitutionTypeDTO create(InstitutionTypeDTO dto) {
        InstitutionTypeEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public InstitutionTypeDTO update(Integer id, InstitutionTypeDTO dto) {
        InstitutionTypeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setName(dto.getName());
        entity.setCommune(mapToCommuneEntity(dto.getCommune()));
        entity.setInstitution(mapToInstitutionEntity(dto.getInstitution()));
        return mapToDTO(repository.save(entity));
    }

    @Override
    public InstitutionTypeDTO getById(Integer id) {
        InstitutionTypeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<InstitutionTypeDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<InstitutionTypeDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<InstitutionTypeDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }





    /*Listar communas activas*/
    public List<InstitutionTypeDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<InstitutionTypeDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<InstitutionTypeDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        InstitutionTypeEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
