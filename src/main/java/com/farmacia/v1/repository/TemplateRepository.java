package com.farmacia.v1.repository;

import com.farmacia.v1.entity.TemplateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity,Integer> {
    @Query(
            value = "SELECT * FROM templates c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<TemplateEntity> findAllDeleted();

    @Query("SELECT ur FROM TemplateEntity ur WHERE ur.deletedAt IS NULL")
    List<TemplateEntity> findAllActive();

    @Query(value = "SELECT * FROM templates c WHERE c.id = :id", nativeQuery = true)
    Optional<TemplateEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM templates", nativeQuery = true)
    List<TemplateEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM TemplateEntity c")
    Page<TemplateEntity> findAllPaginated(Pageable pageable);


}