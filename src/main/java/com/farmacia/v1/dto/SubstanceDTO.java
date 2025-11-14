package com.farmacia.v1.dto;

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
    private String weight_net;
    private String unity;
    private String state;

    private ReceptionDTO reception;
    private SubstanceTypeDTO substanceType;
    private PackagingDTO packaging;
    private CommuneDTO commune;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
