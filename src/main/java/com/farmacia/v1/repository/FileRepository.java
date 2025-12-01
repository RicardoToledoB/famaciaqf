package com.farmacia.v1.repository;


import com.farmacia.v1.entity.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

    @Query(value = "SELECT * FROM files c WHERE c.deleted_at IS NOT NULL", nativeQuery = true)
    List<FileEntity> findAllDeleted();

    @Query("SELECT c FROM FileEntity c WHERE c.deletedAt IS NULL")
    List<FileEntity> findAllActive();

    @Query(value = "SELECT * FROM files c WHERE c.id = :id", nativeQuery = true)
    Optional<FileEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM files", nativeQuery = true)
    List<FileEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM FileEntity c")
    Page<FileEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT f FROM FileEntity f
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<FileEntity> search(@Param("name") String name, Pageable pageable);

    @Query("""
       SELECT f FROM FileEntity f
       WHERE (:receptionId IS NULL OR f.reception.id = :receptionId)
    """)
    Page<FileEntity> searchByReception(@Param("receptionId") Integer receptionId, Pageable pageable);
}
