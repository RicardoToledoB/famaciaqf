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
@Table(name="destructions_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE destructions_details SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class DestructionDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String state;
    private String weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destruction_header_id")
    private DestructionHeaderEntity destructionHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="substance_id")
    private SubstanceEntity substance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storage_id",nullable = true)
    private StorageEntity storage;

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
