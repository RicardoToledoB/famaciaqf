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
@Table(name="substances")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE substances SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class SubstanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nue;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String weight;

    private String weight_net;
    private String unity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reception_id")
    private ReceptionEntity reception;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="substance_type_id")
    private SubstanceTypeEntity substanceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="packaging_id")
    private PackagingEntity packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="commune_id")
    private CommuneEntity commune;

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
