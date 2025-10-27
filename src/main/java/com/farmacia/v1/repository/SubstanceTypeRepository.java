package com.farmacia.v1.repository;

import com.farmacia.v1.entity.SubstanceTypeEntity;
import com.farmacia.v1.entity.SubstanceTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubstanceTypeRepository extends JpaRepository<SubstanceTypeEntity,Integer> {
    @Query(
            value = "SELECT * FROM substances_types c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<SubstanceTypeEntity> findAllDeleted();

    @Query("SELECT ur FROM SubstanceTypeEntity ur WHERE ur.deletedAt IS NULL")
    List<SubstanceTypeEntity> findAllActive();

    @Query(value = "SELECT * FROM substances_types c WHERE c.id = :id", nativeQuery = true)
    Optional<SubstanceTypeEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM substances_types", nativeQuery = true)
    List<SubstanceTypeEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM SubstanceTypeEntity c")
    Page<SubstanceTypeEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM SubstanceTypeEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<SubstanceTypeEntity> search(@Param("name") String name, Pageable pageable);
}
