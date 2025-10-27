package com.farmacia.v1.repository;

import com.farmacia.v1.entity.LocationEntity;
import com.farmacia.v1.entity.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Integer> {
    @Query(
            value = "SELECT * FROM locations c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<LocationEntity> findAllDeleted();

    @Query("SELECT ur FROM LocationEntity ur WHERE ur.deletedAt IS NULL")
    List<LocationEntity> findAllActive();

    @Query(value = "SELECT * FROM locations c WHERE c.id = :id", nativeQuery = true)
    Optional<LocationEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM locations", nativeQuery = true)
    List<LocationEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM LocationEntity c")
    Page<LocationEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM LocationEntity c
       WHERE (:name IS NULL OR TRIM(:name) = '' 
              OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)
    Page<LocationEntity> search(@Param("name") String name, Pageable pageable);
}
