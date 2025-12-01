package com.farmacia.v1.repository;

import com.farmacia.v1.entity.ChemicalTestEntity;
import com.farmacia.v1.entity.ChemicalTestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChemicalTestRepository extends JpaRepository<ChemicalTestEntity,Integer> {
    @Query(
            value = "SELECT * FROM chemical_tests c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<ChemicalTestEntity> findAllDeleted();

    @Query("SELECT ur FROM ChemicalTestEntity ur WHERE ur.deletedAt IS NULL")
    List<ChemicalTestEntity> findAllActive();

    @Query(value = "SELECT * FROM chemical_tests c WHERE c.id = :id", nativeQuery = true)
    Optional<ChemicalTestEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM chemical_tests", nativeQuery = true)
    List<ChemicalTestEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM ChemicalTestEntity c")
    Page<ChemicalTestEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM ChemicalTestEntity c
       WHERE (:result IS NULL OR TRIM(:result) = '' 
              OR LOWER(c.result) LIKE LOWER(CONCAT('%', :result, '%')))
    """)
    Page<ChemicalTestEntity> search(@Param("result") String result, Pageable pageable);
}
