package com.farmacia.v1.repository;

import com.farmacia.v1.entity.InstitutionTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionTypeRepository extends JpaRepository<InstitutionTypeEntity,Integer> {
    @Query(
            value = "SELECT * FROM communes c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<InstitutionTypeEntity> findAllDeleted();

    @Query("SELECT ur FROM InstitutionTypeEntity ur WHERE ur.deletedAt IS NULL")
    List<InstitutionTypeEntity> findAllActive();

    @Query(value = "SELECT * FROM communes c WHERE c.id = :id", nativeQuery = true)
    Optional<InstitutionTypeEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM communes", nativeQuery = true)
    List<InstitutionTypeEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM InstitutionTypeEntity c")
    Page<InstitutionTypeEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM InstitutionTypeEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<InstitutionTypeEntity> search(@Param("name") String name, Pageable pageable);
}
