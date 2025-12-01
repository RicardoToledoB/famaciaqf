package com.farmacia.v1.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE files SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;        // nombre original
    private String type;        // MIME type
    private String path;        // ruta local (p.ej. uploads/files/123_nombre.pdf)
    private Long size;          // tamaño bytes
    private String description; // comentario opcional
    private String checksum;    // sha256

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id")
    private ReceptionEntity reception;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    private void createdAt() { this.createdAt = LocalDateTime.now(); }

    @PreUpdate
    private void updatedAt() { this.updatedAt = LocalDateTime.now(); }
}
