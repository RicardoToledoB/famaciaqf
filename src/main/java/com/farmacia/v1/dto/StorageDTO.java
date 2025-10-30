package com.farmacia.v1.dto;

import com.farmacia.v1.entity.StorageLocationEntity;
import com.farmacia.v1.entity.SubstanceEntity;
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
public class StorageDTO {
    private Integer id;
    private String entry_date;
    private String sample_quantity;
    private String counter_sample_quantity;
    private String description;
    private SubstanceDTO substance;
    private StorageLocationDTO storageLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
