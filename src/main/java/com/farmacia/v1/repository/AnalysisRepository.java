package com.farmacia.v1.repository;

import com.farmacia.v1.entity.AnalysisEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity,Integer> {
    @Query(
            value = "SELECT * FROM analysis c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<AnalysisEntity> findAllDeleted();

    @Query("SELECT ur FROM AnalysisEntity ur WHERE ur.deletedAt IS NULL")
    List<AnalysisEntity> findAllActive();

    @Query(value = "SELECT * FROM grades c WHERE c.id = :id", nativeQuery = true)
    Optional<AnalysisEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM grades", nativeQuery = true)
    List<AnalysisEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM AnalysisEntity c")
    Page<AnalysisEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM AnalysisEntity c
       WHERE (:number_protocol IS NULL OR TRIM(:number_protocol) = '' 
              OR LOWER(c.number_protocol) LIKE LOWER(CONCAT('%', :number_protocol, '%')))
    """)
    Page<AnalysisEntity> search(@Param("number_protocol") String number_protocol, Pageable pageable);
}