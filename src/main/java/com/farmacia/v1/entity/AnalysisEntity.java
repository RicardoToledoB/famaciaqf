package com.farmacia.v1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name="analysis")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE analysis SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number_protocol;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String date_analysis;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Column(columnDefinition = "TEXT")
    private String macro;

    @Column(columnDefinition = "TEXT")
    private String micro;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="template_id")
    private TemplateEntity template;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_analysis_id")
    private PreAnalysisEntity preAnalysis;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    @PrePersist
    private void createdAt(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updatedAt(){
        this.updatedAt = LocalDateTime.now();
    }
}
