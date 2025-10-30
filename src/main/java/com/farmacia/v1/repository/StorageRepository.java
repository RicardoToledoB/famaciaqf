package com.farmacia.v1.repository;

import com.farmacia.v1.entity.StorageEntity;
import com.farmacia.v1.entity.StorageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity,Integer> {
    @Query(
            value = "SELECT * FROM storages c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<StorageEntity> findAllDeleted();

    @Query("SELECT ur FROM StorageEntity ur WHERE ur.deletedAt IS NULL")
    List<StorageEntity> findAllActive();

    @Query(value = "SELECT * FROM storages c WHERE c.id = :id", nativeQuery = true)
    Optional<StorageEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM storages", nativeQuery = true)
    List<StorageEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM StorageEntity c")
    Page<StorageEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM StorageEntity c
       WHERE (:entry_date IS NULL OR TRIM(:entry_date) = '' 
              OR LOWER(c.entry_date) LIKE LOWER(CONCAT('%', :entry_date, '%')))
    """)
    Page<StorageEntity> search(@Param("entry_date") String entry_date, Pageable pageable);
}