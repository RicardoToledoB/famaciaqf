package com.farmacia.v1.repository;

import com.farmacia.v1.entity.PackagingEntity;
import com.farmacia.v1.entity.PackagingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackagingRepository extends JpaRepository<PackagingEntity,Integer> {
    @Query(
            value = "SELECT * FROM packagings c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<PackagingEntity> findAllDeleted();

    @Query("SELECT ur FROM PackagingEntity ur WHERE ur.deletedAt IS NULL")
    List<PackagingEntity> findAllActive();

    @Query(value = "SELECT * FROM packagings c WHERE c.id = :id", nativeQuery = true)
    Optional<PackagingEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM packagings", nativeQuery = true)
    List<PackagingEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM PackagingEntity c")
    Page<PackagingEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM PackagingEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<PackagingEntity> search(@Param("name") String name, Pageable pageable);
}
