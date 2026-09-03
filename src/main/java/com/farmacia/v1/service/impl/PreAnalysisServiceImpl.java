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
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class PreAnalysisServiceImpl implements IPreAnalysisService {

    @Autowired
    private PreAnalysisRepository repository;

    /**
     * Evita que el listado completo falle cuando existe una referencia antigua
     * hacia una entidad relacionada eliminada por soft-delete o inexistente.
     * En ese caso se retorna null para la relación, sin interrumpir la respuesta
     * completa del endpoint /api/v1/pre_analysis.
     */
    private <T> T safeMap(Supplier<T> mapper) {
        try {
            return mapper.get();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }


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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> SubstanceDTO.builder()
                .id(entity.getId())
                .nue(entity.getNue())
                .description(entity.getDescription())
                .weight(entity.getWeight())
                .weight_net(entity.getWeight_net())
                .unity(entity.getUnity())
                .state(entity.getState())
                .nsubstance(entity.getNsubstance())
                .reception(mapToReceptionDTO(entity.getReception()))
                .substanceType(mapToSubstanceTypeDTO(entity.getSubstanceType()))
                .packaging(mapToPackagingDTO(entity.getPackaging()))
                .commune(mapToCommuneDTO(entity.getCommune()))
                .measurement_type(entity.getMeasurement_type())
                .unit_quantity(entity.getUnit_quantity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private SubstanceEntity mapToSubstanceEntity(SubstanceDTO dto) {
        if (dto == null) {
            return null;
        }

        return SubstanceEntity.builder()
                .id(dto.getId())
                .nue(dto.getNue())
                .description(dto.getDescription())
                .weight(dto.getWeight())
                .weight_net(dto.getWeight_net())
                .unity(dto.getUnity())
                .state(dto.getState())
                .nsubstance(dto.getNsubstance())
                .reception(mapToReceptionEntity(dto.getReception()))
                .substanceType(mapToSubstanceTypeEntity(dto.getSubstanceType()))
                .packaging(mapToPackagingEntity(dto.getPackaging()))
                .commune(mapToCommuneEntity(dto.getCommune()))
                .measurement_type(dto.getMeasurement_type())
                .unit_quantity(dto.getUnit_quantity())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private ReceptionDTO mapToReceptionDTO(ReceptionEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> ReceptionDTO.builder()
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
                .build());
    }

    private ReceptionEntity mapToReceptionEntity(ReceptionDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> LocationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private LocationEntity mapToLocationEntity(LocationDTO dto) {
        if (dto == null) {
            return null;
        }

        return LocationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private PoliceDTO mapToPoliceDTO(PoliceEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> PoliceDTO.builder()
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
                .build());
    }

    private PoliceEntity mapToPoliceEntity(PoliceDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> InstitutionTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .commune(mapToCommuneDTO(entity.getCommune()))
                .institution(mapToInstitutionDTO(entity.getInstitution()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private InstitutionTypeEntity mapToInstitutionTypeEntity(InstitutionTypeDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> CommuneDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private CommuneEntity mapToCommuneEntity(CommuneDTO dto) {
        if (dto == null) {
            return null;
        }

        return CommuneEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }


    private InstitutionDTO mapToInstitutionDTO(InstitutionEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> InstitutionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private InstitutionEntity mapToInstitutionEntity(InstitutionDTO dto) {
        if (dto == null) {
            return null;
        }

        return InstitutionEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }




    private GradeDTO mapToGradeDTO(GradeEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> GradeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .institutionType(mapToInstitutionTypeDTO(entity.getInstitutionType()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private GradeEntity mapToGradeEntity(GradeDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> UserDTO.builder()
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
                .build());
    }

    private UserEntity mapToUserEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

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
        if (entity == null) {
            return null;
        }

        return safeMap(() -> SubstanceTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private SubstanceTypeEntity mapToSubstanceTypeEntity(SubstanceTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        return SubstanceTypeEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private PackagingDTO mapToPackagingDTO(PackagingEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> PackagingDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private PackagingEntity mapToPackagingEntity(PackagingDTO dto) {
        if (dto == null) {
            return null;
        }

        return PackagingEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }


    private DestinationDTO mapToDestinationDTO(DestinationEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> DestinationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private DestinationEntity mapToDestinationEntity(DestinationDTO dto) {
        if (dto == null) {
            return null;
        }

        return DestinationEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }

    private MethodDestructionDTO mapToMethodDestructionDTO(MethodDestructionEntity entity) {
        if (entity == null) {
            return null;
        }

        return safeMap(() -> MethodDestructionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build());
    }

    private MethodDestructionEntity mapToMethodDestructionEntity(MethodDestructionDTO dto) {
        if (dto == null) {
            return null;
        }

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
