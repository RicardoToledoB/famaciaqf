package com.farmacia.v1.repository;

import com.farmacia.v1.entity.DestinationEntity;
import com.farmacia.v1.entity.DestinationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity,Integer> {
    @Query(
            value = "SELECT * FROM destinations c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<DestinationEntity> findAllDeleted();

    @Query("SELECT ur FROM DestinationEntity ur WHERE ur.deletedAt IS NULL")
    List<DestinationEntity> findAllActive();

    @Query(value = "SELECT * FROM destinations c WHERE c.id = :id", nativeQuery = true)
    Optional<DestinationEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM destinations", nativeQuery = true)
    List<DestinationEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM DestinationEntity c")
    Page<DestinationEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM DestinationEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<DestinationEntity> search(@Param("name") String name, Pageable pageable);
}
