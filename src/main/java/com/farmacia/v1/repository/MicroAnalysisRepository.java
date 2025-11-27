package com.farmacia.v1.repository;

import com.farmacia.v1.entity.MicroAnalysisEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MicroAnalysisRepository extends JpaRepository<MicroAnalysisEntity,Integer> {
    @Query(
            value = "SELECT * FROM microanalysis c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<MicroAnalysisEntity> findAllDeleted();

    @Query("SELECT ur FROM MicroAnalysisEntity ur WHERE ur.deletedAt IS NULL")
    List<MicroAnalysisEntity> findAllActive();

    @Query(value = "SELECT * FROM grades c WHERE c.id = :id", nativeQuery = true)
    Optional<MicroAnalysisEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM grades", nativeQuery = true)
    List<MicroAnalysisEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM MicroAnalysisEntity c")
    Page<MicroAnalysisEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM MicroAnalysisEntity c
       WHERE (:ttgland IS NULL OR TRIM(:ttgland) = '' 
              OR LOWER(c.ttgland) LIKE LOWER(CONCAT('%', :ttgland, '%')))
    """)
    Page<MicroAnalysisEntity> search(@Param("ttgland") String ttgland, Pageable pageable);
}