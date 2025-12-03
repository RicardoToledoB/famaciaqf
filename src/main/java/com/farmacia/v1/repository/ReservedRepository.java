package com.farmacia.v1.repository;

import com.farmacia.v1.entity.ReservedEntity;
import com.farmacia.v1.entity.ReservedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservedRepository extends JpaRepository<ReservedEntity,Integer> {
    @Query(
            value = "SELECT * FROM reserveds c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<ReservedEntity> findAllDeleted();

    @Query("SELECT ur FROM ReservedEntity ur WHERE ur.deletedAt IS NULL")
    List<ReservedEntity> findAllActive();

    @Query(value = "SELECT * FROM reserveds c WHERE c.id = :id", nativeQuery = true)
    Optional<ReservedEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM reserveds", nativeQuery = true)
    List<ReservedEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM ReservedEntity c")
    Page<ReservedEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM ReservedEntity c
       WHERE (:number IS NULL OR TRIM(:number) = '' 
              OR LOWER(c.number) LIKE LOWER(CONCAT('%', :number, '%')))
    """)
    Page<ReservedEntity> search(@Param("number") String number, Pageable pageable);
}
