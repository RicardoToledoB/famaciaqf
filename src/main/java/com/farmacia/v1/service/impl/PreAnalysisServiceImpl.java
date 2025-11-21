package com.farmacia.v1.service.impl;

import com.farmacia.v1.dto.*;
import com.farmacia.v1.entity.*;
import com.farmacia.v1.repository.GradeRepository;
import com.farmacia.v1.repository.PreAnalysisRepository;
import com.farmacia.v1.service.IGradeService;
import com.farmacia.v1.service.IPreAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreAnalysisServiceImpl implements IPreAnalysisService {

    @Autowired
    private PreAnalysisRepository repository;


    /*
      private String weightContra;
    private String weightDestruction;

    */


    private PreAnalysisDTO mapToDTO(PreAnalysisEntity entity) {
        return PreAnalysisDTO.builder()
                .id(entity.getId())
                .weight_sampled(entity.getWeight_sampled())
                .observation(entity.getObservation())
                .reception(mapToReceptionDTO(entity.getReception()))
                .substance(mapToSubstanceDTO(entity.getSubstance()))
                .weightContra(entity.getWeightContra())
                .weightDestruction(entity.getWeightDestruction())
                .destination(mapToDestinationDTO(entity.getDestination()))
                .methodDestruction(mapToMethodDestructionDTO(entity.getMethodDestruction()))
                .user(mapToUserDTO(entity.getUser()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    /*
     private String weightContra;
   private String weightDestruction;

   */

    private PreAnalysisEntity mapToEntity(PreAnalysisDTO dto) {
        return PreAnalysisEntity.builder()
                .id(dto.getId())
                .weight_sampled(dto.getWeight_sampled())
                .observation(dto.getObservation())
                .weightContra(dto.getWeightContra())
                .weightDestruction(dto.getWeightDestruction())
                .reception(mapToReceptionEntity(dto.getReception()))
                .substance(mapToSubstanceEntity(dto.getSubstance()))
                .destination(mapToDestinationEntity(dto.getDestination()))
                .methodDestruction(mapToMethodDestructionEntity(dto.getMethodDestruction()))
                .user(mapToUserEntity(dto.getUser()))
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


    private DestinationDTO mapToDestinationDTO(DestinationEntity entity) {
        return DestinationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    private DestinationEntity mapToDestinationEntity(DestinationDTO dto) {
        return DestinationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
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



    public PreAnalysisDTO create(PreAnalysisDTO dto) {
        PreAnalysisEntity entity = repository.save(mapToEntity(dto));
        return mapToDTO(entity);
    }


    /*
    private String weightContra;
  private String weightDestruction;

  */

    @Override
    public PreAnalysisDTO update(Integer id, PreAnalysisDTO dto) {
        PreAnalysisEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setWeight_sampled(dto.getWeight_sampled());
        entity.setObservation(dto.getObservation());
        entity.setWeightContra(dto.getWeightContra());
        entity.setWeightDestruction(dto.getWeightDestruction());
        entity.setReception(mapToReceptionEntity(dto.getReception()));
        entity.setSubstance(mapToSubstanceEntity(dto.getSubstance()));
        entity.setDestination(mapToDestinationEntity(dto.getDestination()));
        entity.setMethodDestruction(mapToMethodDestructionEntity(dto.getMethodDestruction()));
        entity.setUser(mapToUserEntity(dto.getUser()));
        return mapToDTO(repository.save(entity));
    }

    @Override
    public PreAnalysisDTO getById(Integer id) {
        PreAnalysisEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<PreAnalysisDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    public Page<PreAnalysisDTO> getAllPaginated(Pageable pageable) {
        return repository.findAllPaginated(pageable)
                .map(this::mapToDTO);
    }






    /*Listar communas activas*/
    public List<PreAnalysisDTO> listAll() {
        return repository.findAllIncludingDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public List<PreAnalysisDTO> listActive() {
        return repository.findAllActive().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public List<PreAnalysisDTO> listDeleted() {
        return repository.findAllDeleted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void restore(Integer id) {
        PreAnalysisEntity entity = repository.findAnyById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}
