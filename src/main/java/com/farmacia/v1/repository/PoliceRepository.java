package com.farmacia.v1.repository;

import com.farmacia.v1.entity.PoliceEntity;
import com.farmacia.v1.entity.PoliceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliceRepository extends JpaRepository<PoliceEntity,Integer> {
    @Query(
            value = "SELECT * FROM polices c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<PoliceEntity> findAllDeleted();

    @Query("SELECT ur FROM PoliceEntity ur WHERE ur.deletedAt IS NULL")
    List<PoliceEntity> findAllActive();

    @Query(value = "SELECT * FROM polices c WHERE c.id = :id", nativeQuery = true)
    Optional<PoliceEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM polices", nativeQuery = true)
    List<PoliceEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM PoliceEntity c")
    Page<PoliceEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM PoliceEntity c
       WHERE (:rut IS NULL OR TRIM(:rut) = '' 
              OR LOWER(c.rut) LIKE LOWER(CONCAT('%', :rut, '%')))
    """)
    Page<PoliceEntity> search(@Param("rut") String rut, Pageable pageable);
}