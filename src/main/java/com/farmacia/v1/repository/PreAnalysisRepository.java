package com.farmacia.v1.repository;

import com.farmacia.v1.entity.PreAnalysisEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreAnalysisRepository extends JpaRepository<PreAnalysisEntity,Integer> {
    @Query(
            value = "SELECT * FROM pre_analysis c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<PreAnalysisEntity> findAllDeleted();

    @Query("SELECT ur FROM PreAnalysisEntity ur WHERE ur.deletedAt IS NULL")
    List<PreAnalysisEntity> findAllActive();

    @Query(value = "SELECT * FROM pre_analysis c WHERE c.id = :id", nativeQuery = true)
    Optional<PreAnalysisEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM pre_analysis", nativeQuery = true)
    List<PreAnalysisEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM PreAnalysisEntity c")
    Page<PreAnalysisEntity> findAllPaginated(Pageable pageable);


}