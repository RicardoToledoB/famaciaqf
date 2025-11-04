package com.farmacia.v1.repository;

import com.farmacia.v1.entity.DestructionHeaderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestructionHeaderRepository extends JpaRepository<DestructionHeaderEntity,Integer> {
    @Query(
            value = "SELECT * FROM destructions_headers c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<DestructionHeaderEntity> findAllDeleted();

    @Query("SELECT ur FROM DestructionHeaderEntity ur WHERE ur.deletedAt IS NULL")
    List<DestructionHeaderEntity> findAllActive();

    @Query(value = "SELECT * FROM destructions_headers c WHERE c.id = :id", nativeQuery = true)
    Optional<DestructionHeaderEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM destructions_headers", nativeQuery = true)
    List<DestructionHeaderEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM DestructionHeaderEntity c")
    Page<DestructionHeaderEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM DestructionHeaderEntity c
       WHERE (:act_number IS NULL OR TRIM(:act_number) = '' 
              OR LOWER(c.act_number) LIKE LOWER(CONCAT('%', :act_number, '%')))
    """)
    Page<DestructionHeaderEntity> search(@Param("act_number") String act_number, Pageable pageable);
}