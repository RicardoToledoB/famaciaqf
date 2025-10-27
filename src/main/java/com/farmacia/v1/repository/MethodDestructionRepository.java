package com.farmacia.v1.repository;

import com.farmacia.v1.entity.MethodDestructionEntity;
import com.farmacia.v1.entity.MethodDestructionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MethodDestructionRepository extends JpaRepository<MethodDestructionEntity,Integer> {
    @Query(
            value = "SELECT * FROM methods_destructions c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<MethodDestructionEntity> findAllDeleted();

    @Query("SELECT ur FROM MethodDestructionEntity ur WHERE ur.deletedAt IS NULL")
    List<MethodDestructionEntity> findAllActive();

    @Query(value = "SELECT * FROM methods_destructions c WHERE c.id = :id", nativeQuery = true)
    Optional<MethodDestructionEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM methods_destructions", nativeQuery = true)
    List<MethodDestructionEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM MethodDestructionEntity c")
    Page<MethodDestructionEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM MethodDestructionEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<MethodDestructionEntity> search(@Param("name") String name, Pageable pageable);
}
