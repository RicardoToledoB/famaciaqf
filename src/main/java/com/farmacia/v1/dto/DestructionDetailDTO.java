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
public class DestructionDetailDTO {
    private Integer id;
    private String state;
    private String weight;
    private DestructionHeaderDTO destructionHeader;
    private SubstanceDTO substance;
    private StorageDTO storage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
