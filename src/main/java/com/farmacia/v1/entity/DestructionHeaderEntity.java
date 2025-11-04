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
@Table(name="destructions_headers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE destructions_headers SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class DestructionHeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String act_number;
    private String date_destruction;
    @Column(columnDefinition = "TEXT")
    private String observation;
    private String state;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="method_destruction_id")
    private MethodDestructionEntity methodDestruction;


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
