package com.farmacia.v1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name="templates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE templates SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JdbcTypeCode(SqlTypes.JSON)          // Hibernate mapea a JSON nativo si existe
    @Column(columnDefinition = "json")    // "jsonb" en Postgres si prefieres
    private Map<String, Object> text_json;


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
