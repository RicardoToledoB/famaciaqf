package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.TemplateDTO;
import com.farmacia.v1.dto.InstitutionTypeDTO;
import com.farmacia.v1.entity.TemplateEntity;
import com.farmacia.v1.entity.InstitutionTypeEntity;
import com.farmacia.v1.repository.GradeRepository;
import com.farmacia.v1.repository.TemplateRepository;
import com.farmacia.v1.service.IGradeService;
import com.farmacia.v1.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private TemplateRepository repository;


    private TemplateDTO mapToDTO(TemplateEntity entity) {
        return TemplateDTO.builder()
                .id(entity.getId())
                .text_json(entity.getText_json())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private TemplateEntity mapToEntity(TemplateDTO dto) {
        return TemplateEntity.builder()
                .id(dto.getId())
                .text_json(dto.getText_json())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }



    public TemplateDTO create(TemplateDTO dto) {
        TemplateEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public TemplateDTO update(Integer id, TemplateDTO dto) {
        TemplateEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setText_json(dto.getText_json());
        return mapToDTO(repository.save(entity));
    }

    @Override
    public TemplateDTO getById(Integer id) {
        TemplateEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<TemplateDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<TemplateDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }







    /*Listar communas activas*/
    public List<TemplateDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<TemplateDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<TemplateDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        TemplateEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
