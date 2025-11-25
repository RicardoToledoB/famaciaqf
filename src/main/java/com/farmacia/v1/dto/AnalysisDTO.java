package com.farmacia.v1.dto;

import com.farmacia.v1.entity.PreAnalysisEntity;
import com.farmacia.v1.entity.TemplateEntity;
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
public class AnalysisDTO {
    private Integer id;
    private String number_protocol;
    private String description;
    private String date_analysis;
    private String gradeFrac;
    private String gradeHum;
    private String color;
    private String smell;
    private String composition;
    private String result;
    private String macro;
    private String micro;
    private String state;
    private UserDTO user;
    private PreAnalysisDTO preAnalysis;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
