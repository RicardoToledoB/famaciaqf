package com.farmacia.v1.repository;

import com.farmacia.v1.entity.InstitutionEntity;
import com.farmacia.v1.entity.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity,Integer> {
    @Query(
            value = "SELECT * FROM institutions c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<InstitutionEntity> findAllDeleted();

    @Query("SELECT ur FROM InstitutionEntity ur WHERE ur.deletedAt IS NULL")
    List<InstitutionEntity> findAllActive();

    @Query(value = "SELECT * FROM institutions c WHERE c.id = :id", nativeQuery = true)
    Optional<InstitutionEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM institutions", nativeQuery = true)
    List<InstitutionEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM InstitutionEntity c")
    Page<InstitutionEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM InstitutionEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<InstitutionEntity> search(@Param("name") String name, Pageable pageable);
}