package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.*;
import com.farmacia.v1.entity.*;
import com.farmacia.v1.repository.CommuneRepository;
import com.farmacia.v1.repository.DestructionDetailRepository;
import com.farmacia.v1.service.ICommuneService;
import com.farmacia.v1.service.IDestructionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestructionDetailServiceImpl implements IDestructionDetailService {

    @Autowired
    private DestructionDetailRepository repository;


    private DestructionDetailDTO mapToDTO(DestructionDetailEntity entity) {
        return DestructionDetailDTO.builder()
                .id(entity.getId())
                .state(entity.getState())
                .weight(entity.getWeight())
                .destructionHeader(mapToDestructionHeaderDTO(entity.getDestructionHeader()))
                .storage(entity.getStorage() != null && entity.getStorage().getId() != null
                        ? mapToStorageDTO(entity.getStorage())
                        : null)
                .substance(mapToSubstanceDTO(entity.getSubstance()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }



    private DestructionDetailEntity mapToEntity(DestructionDetailDTO dto) {
        return DestructionDetailEntity.builder()
                .id(dto.getId())
                .state(dto.getState())
                .weight(dto.getWeight())
                .destructionHeader(mapToDestructionHeaderEntity(dto.getDestructionHeader()))
                .storage(dto.getStorage() != null && dto.getStorage().getId() != null
                        ? mapToStorageEntity(dto.getStorage())
                        : null)
                .substance(mapToSubstanceEntity(dto.getSubstance()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private StorageDTO mapToStorageDTO(StorageEntity entity) {
        return StorageDTO.builder()
                .id(entity.getId())
                .entry_date(entity.getEntry_date())
                .sample_quantity(entity.getSample_quantity())
                .counter_sample_quantity(entity.getCounter_sample_quantity())
                .description(entity.getDescription())
                .state(entity.getState())
                .substance(mapToSubstanceDTO(entity.getSubstance()))
                .storageLocation(mapToStorageLocationDTO(entity.getStorageLocation()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private StorageEntity mapToStorageEntity(StorageDTO dto) {
        return StorageEntity.builder()
                .id(dto.getId())
                .entry_date(dto.getEntry_date())
                .sample_quantity(dto.getSample_quantity())
                .counter_sample_quantity(dto.getCounter_sample_quantity())
                .description(dto.getDescription())
                .state(dto.getState())
                .substance(mapToSubstanceEntity(dto.getSubstance()))
                .storageLocation(mapToStorageLocationEntity(dto.getStorageLocation()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private SubstanceDTO mapToSubstanceDTO(SubstanceEntity entity) {
        return SubstanceDTO.builder()
                .id(entity.getId())
                .nue(entity.getNue())
                .description(entity.getDescription())
                .weight(entity.getWeight())
                .reception(mapToReceptionDTO(entity.getReception()))
                .substanceType(mapToSubstanceTypeDTO(entity.getSubstanceType()))
                .packaging(mapToPackagingDTO(entity.getPackaging()))
                .commune(mapToCommuneDTO(entity.getCommune()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private SubstanceEntity mapToSubstanceEntity(SubstanceDTO dto) {
        return SubstanceEntity.builder()
                .id(dto.getId())
                .nue(dto.getNue())
                .description(dto.getDescription())
                .weight(dto.getWeight())
                .reception(mapToReceptionEntity(dto.getReception()))
                .substanceType(mapToSubstanceTypeEntity(dto.getSubstanceType()))
                .packaging(mapToPackagingEntity(dto.getPackaging()))
                .commune(mapToCommuneEntity(dto.getCommune()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private ReceptionDTO mapToReceptionDTO(ReceptionEntity entity) {
        return ReceptionDTO.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .date_reception(entity.getDate_reception())
                .of_number(entity.getOf_number())
                .of_number_date(entity.getOf_number_date())
                .location(mapToLocationDTO(entity.getLocation()))
                .police(mapToPoliceDTO(entity.getPolice()))
                .user_origin(mapToUserDTO(entity.getUser_origin()))
                .user_destination(mapToUserDTO(entity.getUser_destination()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private ReceptionEntity mapToReceptionEntity(ReceptionDTO dto) {
        return ReceptionEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .date_reception(dto.getDate_reception())
                .of_number(dto.getOf_number())
                .of_number_date(dto.getOf_number_date())
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






    private SubstanceTypeDTO mapToSubstanceTypeDTO(SubstanceTypeEntity entity) {
        return SubstanceTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private SubstanceTypeEntity mapToSubstanceTypeEntity(SubstanceTypeDTO dto) {
        return SubstanceTypeEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private PackagingDTO mapToPackagingDTO(PackagingEntity entity) {
        return PackagingDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private PackagingEntity mapToPackagingEntity(PackagingDTO dto) {
        return PackagingEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private StorageLocationDTO mapToStorageLocationDTO(StorageLocationEntity entity) {
        return StorageLocationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private StorageLocationEntity mapToStorageLocationEntity(StorageLocationDTO dto) {
        return StorageLocationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }


    private DestructionHeaderDTO mapToDestructionHeaderDTO(DestructionHeaderEntity entity) {
        return DestructionHeaderDTO.builder()
                .id(entity.getId())
                .act_number(entity.getAct_number())
                .date_destruction(entity.getDate_destruction())
                .observation(entity.getObservation())
                .state(entity.getState())
                .weight(entity.getWeight())
                .methodDestruction(mapToMethodDestructionDTO(entity.getMethodDestruction()))
                .user(mapToUserDTO(entity.getUser()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private DestructionHeaderEntity mapToDestructionHeaderEntity(DestructionHeaderDTO dto) {
        return DestructionHeaderEntity.builder()
                .id(dto.getId())
                .act_number(dto.getAct_number())
                .date_destruction(dto.getDate_destruction())
                .observation(dto.getObservation())
                .state(dto.getState())
                .weight(dto.getWeight())
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

    public DestructionDetailDTO create(DestructionDetailDTO dto) {
        DestructionDetailEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }

    @Override
    public DestructionDetailDTO update(Integer id, DestructionDetailDTO dto) {
        DestructionDetailEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setState(dto.getState());
        entity.setWeight(dto.getWeight());
        entity.setDestructionHeader(mapToDestructionHeaderEntity(dto.getDestructionHeader()));
        entity.setSubstance(mapToSubstanceEntity(dto.getSubstance()));
        entity.setStorage(dto.getStorage() != null && dto.getStorage().getId() != null
                ? mapToStorageEntity(dto.getStorage())
                : null);
        return mapToDTO(repository.save(entity));
    }

    @Override
    public DestructionDetailDTO getById(Integer id) {
        DestructionDetailEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<DestructionDetailDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<DestructionDetailDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }

    public Page<DestructionDetailDTO> getAllPaginated(String name, Pageable pageable) {
        return repository.search(name, pageable).map(this::mapToDTO);
    }





    /*Listar communas activas*/
    public List<DestructionDetailDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<DestructionDetailDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<DestructionDetailDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        DestructionDetailEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }

    public List<DestructionDetailDTO> findByDestructionHeaderId(Integer headerId) {
        return repository.findByDestructionHeader_Id(headerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
