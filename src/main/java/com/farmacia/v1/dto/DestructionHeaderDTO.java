package com.farmacia.v1.dto;

import com.farmacia.v1.entity.InstitutionTypeEntity;
import com.farmacia.v1.entity.MethodDestructionEntity;
import com.farmacia.v1.entity.UserEntity;
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
public class DestructionHeaderDTO {
    private Integer id;
    private String act_number;
    private String date_destruction;
    private String observation;
    private String state;
    private MethodDestructionDTO methodDestruction;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
