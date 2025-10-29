package com.farmacia.v1.dto;

import com.farmacia.v1.entity.CommuneEntity;
import com.farmacia.v1.entity.PackagingEntity;
import com.farmacia.v1.entity.ReceptionEntity;
import com.farmacia.v1.entity.SubstanceTypeEntity;
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
public class SubstanceDTO {
    private Integer id;
    private String nue;
    private String description;
    private String weight;
    private ReceptionDTO reception;
    private SubstanceTypeDTO substanceType;
    private PackagingDTO packaging;
    private CommuneDTO commune;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
