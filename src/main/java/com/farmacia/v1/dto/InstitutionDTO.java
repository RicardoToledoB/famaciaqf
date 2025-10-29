package com.farmacia.v1.dto;

import com.farmacia.v1.entity.InstitutionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstitutionDTO {
    private Integer id;
    private String name;
    private InstitutionTypeDTO institutionType;
    private CommuneDTO commune;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
