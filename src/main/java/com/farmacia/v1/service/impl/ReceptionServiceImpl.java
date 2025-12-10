package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.*;
import com.farmacia.v1.entity.*;
import com.farmacia.v1.repository.GradeRepository;
import com.farmacia.v1.repository.ReceptionRepository;
import com.farmacia.v1.service.IGradeService;
import com.farmacia.v1.service.IReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceptionServiceImpl implements IReceptionService {

    @Autowired
    private ReceptionRepository repository;


    private ReceptionDTO mapToDTO(ReceptionEntity entity) {
        return ReceptionDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .date_reception(entity.getDate_reception())
                .of_number(entity.getOf_number())
                .of_number_date(entity.getOf_number_date())
                .state(entity.getState())
                .nParte(entity.getNParte())
                .is_editable(entity.getIs_editable())
                .location(mapToLocationDTO(entity.getLocation()))
                .police(mapToPoliceDTO(entity.getPolice()))
                .user_origin(mapToUserDTO(entity.getUser_origin()))
                .user_destination(mapToUserDTO(entity.getUser_destination()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private ReceptionEntity mapToEntity(ReceptionDTO dto) {
        return ReceptionEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .date_reception(dto.getDate_reception())
                .of_number(dto.getOf_number())
                .of_number_date(dto.getOf_number_date())
                .state(dto.getState())
                .nParte(dto.getNParte())
                .is_editable(dto.getIs_editable())
                .location(mapToLocationEntity(dto.getLocation()))
                .police(mapToPoliceEntity(dto.getPolice()))
                .user_origin(mapToUserEntity(dto.getUser_origin()))
                .user_destination(mapToUserEntity(dto.getUser_destination()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private LocationDTO mapToLocationDTO(LocationEntity entity) {
        return LocationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private LocationEntity mapToLocationEntity(LocationDTO dto) {
        return LocationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private PoliceDTO mapToPoliceDTO(PoliceEntity entity) {
        return PoliceDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .firstLastName(entity.getFirstLastName())
                .secondLastName(entity.getSecondLastName())
                .rut(entity.getRut())
                .email(entity.getEmail())
                .cellphone(entity.getCellphone())
                .institutionType(mapToInstitutionTypeDTO(entity.getInstitutionType()))
                .grade(mapToGradeDTO(entity.getGrade()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private PoliceEntity mapToPoliceEntity(PoliceDTO dto) {
        return PoliceEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .firstLastName(dto.getFirstLastName())
                .secondLastName(dto.getSecondLastName())
                .rut(dto.getRut())
                .email(dto.getEmail())
                .cellphone(dto.getCellphone())
                .institutionType(mapToInstitutionTypeEntity(dto.getInstitutionType()))
                .grade(mapToGradeEntity(dto.getGrade()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private InstitutionTypeDTO mapToInstitutionTypeDTO(InstitutionTypeEntity entity) {
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

    private InstitutionTypeEntity mapToInstitutionTypeEntity(InstitutionTypeDTO dto) {
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




    private GradeDTO mapToGradeDTO(GradeEntity entity) {
        return GradeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .institutionType(mapToInstitutionTypeDTO(entity.getInstitutionType()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private GradeEntity mapToGradeEntity(GradeDTO dto) {
        return GradeEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .institutionType(mapToInstitutionTypeEntity(dto.getInstitutionType()))
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



    public ReceptionDTO create(ReceptionDTO dto) {
        ReceptionEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public ReceptionDTO update(Integer id, ReceptionDTO dto) {
        ReceptionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setNumber(dto.getNumber());
        entity.setDate_reception(dto.getDate_reception());
        entity.setOf_number(dto.getOf_number());
        entity.setOf_number_date(dto.getOf_number_date());
        entity.setState(dto.getState());
        entity.setNParte(dto.getNParte());
        entity.setIs_editable(dto.getIs_editable());
        entity.setLocation(mapToLocationEntity(dto.getLocation()));
        entity.setPolice(mapToPoliceEntity(dto.getPolice()));
        entity.setUser_origin(mapToUserEntity(dto.getUser_origin()));
        entity.setUser_destination(mapToUserEntity(dto.getUser_destination()));

        return mapToDTO(repository.save(entity));
    }

    @Override
    public ReceptionDTO getById(Integer id) {
        ReceptionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<ReceptionDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<ReceptionDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<ReceptionDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }

    //searchByState
    public Page<ReceptionDTO> getAllPaginatedByState(String state, Pageable pageable) {
        return repository.searchByState(state, pageable).map(this::mapToDTO);
    }






    /*Listar communas activas*/
    public List<ReceptionDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<ReceptionDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<ReceptionDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        ReceptionEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
