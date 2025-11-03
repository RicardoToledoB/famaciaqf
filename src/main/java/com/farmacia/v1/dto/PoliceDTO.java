package com.farmacia.v1.dto;

import com.farmacia.v1.entity.GradeEntity;
import com.farmacia.v1.entity.InstitutionEntity;
import com.farmacia.v1.entity.InstitutionTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliceDTO {
    private Integer id;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String rut;
    private String email;
    private String cellphone;
    private InstitutionTypeDTO institutionType;
    private GradeDTO grade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
