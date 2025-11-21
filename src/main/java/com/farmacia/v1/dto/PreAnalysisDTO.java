package com.farmacia.v1.dto;

import com.farmacia.v1.entity.*;
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
public class PreAnalysisDTO {
    private Integer id;
    private String weight_sampled;
    private String weightContra;
    private String weightDestruction;
    private String observation;
    private ReceptionDTO reception;
    private SubstanceDTO substance;
    private DestinationDTO destination;
    private MethodDestructionDTO methodDestruction;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
