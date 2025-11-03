package com.farmacia.v1.dto;

import com.farmacia.v1.entity.CommuneEntity;
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
public class InstitutionTypeDTO {
    private Integer id;
    private String name;
    private CommuneDTO commune;
    private InstitutionDTO institution;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
