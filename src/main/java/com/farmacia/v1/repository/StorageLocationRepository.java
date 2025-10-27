package com.farmacia.v1.repository;

import com.farmacia.v1.entity.StorageLocationEntity;
import com.farmacia.v1.entity.StorageLocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocationEntity,Integer> {
    @Query(
            value = "SELECT * FROM storages_locations c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<StorageLocationEntity> findAllDeleted();

    @Query("SELECT ur FROM StorageLocationEntity ur WHERE ur.deletedAt IS NULL")
    List<StorageLocationEntity> findAllActive();

    @Query(value = "SELECT * FROM storages_locations c WHERE c.id = :id", nativeQuery = true)
    Optional<StorageLocationEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM storages_locations", nativeQuery = true)
    List<StorageLocationEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM StorageLocationEntity c")
    Page<StorageLocationEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM StorageLocationEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<StorageLocationEntity> search(@Param("name") String name, Pageable pageable);
}
