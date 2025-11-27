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
@Table(name="microanalysis")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE microanalysis SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class MicroAnalysisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ttgland;
    private String ttnogland;
    private String stomas;
    private String celepi;
    private String celresi;
    private String cris;
    @Column(columnDefinition = "TEXT")
    private String conclution;

    @Column(columnDefinition = "TEXT")
    private String observation;

    private String date;

    private String aumento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="analysis_id")
    private AnalysisEntity analysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

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
