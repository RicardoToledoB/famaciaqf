package com.farmacia.v1.service.impl;



import com.farmacia.v1.dto.FileDTO;
import com.farmacia.v1.dto.ReceptionDTO;
import com.farmacia.v1.dto.UserDTO;
import com.farmacia.v1.entity.FileEntity;
import com.farmacia.v1.repository.FileRepository;
import com.farmacia.v1.repository.ReceptionRepository;
import com.farmacia.v1.repository.UserRepository;
import com.farmacia.v1.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final FileRepository repository;
    private final ReceptionRepository receptionRepository;
    private final UserRepository userRepository;

    private FileDTO mapToDTO(FileEntity e) {
        return FileDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .type(e.getType())
                .path(e.getPath())
                .size(e.getSize())
                .description(e.getDescription())
                .checksum(e.getChecksum())
                .reception(e.getReception() != null ?
                        ReceptionDTO.builder().id(e.getReception().getId()).build() : null)
                .user(e.getUser() != null ?
                        UserDTO.builder().id(e.getUser().getId()).build() : null)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .deletedAt(e.getDeletedAt())
                .build();
    }

    private FileEntity mapToEntity(FileDTO d) {
        return FileEntity.builder()
                .id(d.getId())
                .name(d.getName())
                .type(d.getType())
                .path(d.getPath())
                .size(d.getSize())
                .description(d.getDescription())
                .checksum(d.getChecksum())
                .reception(d.getReception() != null && d.getReception().getId() != null
                        ? receptionRepository.findById(d.getReception().getId()).orElse(null) : null)
                .user(d.getUser() != null && d.getUser().getId() != null
                        ? userRepository.findById(d.getUser().getId()).orElse(null) : null)
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .deletedAt(d.getDeletedAt())
                .build();
    }

    @Override
    public FileDTO create(FileDTO dto) {
        var saved = repository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public FileDTO update(Integer id, FileDTO dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        entity.setPath(dto.getPath());
        entity.setSize(dto.getSize());
        entity.setChecksum(dto.getChecksum());
        if (dto.getReception() != null && dto.getReception().getId() != null) {
            entity.setReception(receptionRepository.findById(dto.getReception().getId()).orElse(null));
        }
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            entity.setUser(userRepository.findById(dto.getUser().getId()).orElse(null));
        }
        return mapToDTO(repository.save(entity));
    }

    @Override
    public FileDTO getById(Integer id) {
        return repository.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    @Override
    public List<FileDTO> getAll() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id); // soft delete por @SQLDelete
    }

    @Override
    public List<FileDTO> listAll() {
        return repository.findAllIncludingDeleted().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FileDTO> listActive() {
        return repository.findAllActive().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FileDTO> listDeleted() {
        return repository.findAllDeleted().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void restore(Integer id) {
        var entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }

    @Override
    public Page<FileDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }

    @Override
    public Page<FileDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable).map(this::mapToDTO);
    }

    @Override
    public Page<FileDTO> getAllPaginatedByReception(Integer receptionId, Pageable pageable) {
        return repository.searchByReception(receptionId, pageable).map(this::mapToDTO);
    }

    public List<FileDTO> findByReception(Integer receptionId) {
        return repository.findByReception_Id(receptionId)
                .stream().map(this::mapToDTO).toList();
    }
}
