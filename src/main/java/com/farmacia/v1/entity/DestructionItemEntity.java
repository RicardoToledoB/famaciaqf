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
@Table(name="destructions_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE destructions_items SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class DestructionItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String weight_destroyed;
    @Column(columnDefinition = "TEXT")
    private String observation;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="institution_type_id")
    private InstitutionTypeEntity institutionType;

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
