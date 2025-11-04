package com.farmacia.v1.repository;

import com.farmacia.v1.entity.ReceptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionRepository extends JpaRepository<ReceptionEntity,Integer> {
    @Query(
            value = "SELECT * FROM receptions c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<ReceptionEntity> findAllDeleted();

    @Query("SELECT ur FROM ReceptionEntity ur WHERE ur.deletedAt IS NULL")
    List<ReceptionEntity> findAllActive();

    @Query(value = "SELECT * FROM receptions c WHERE c.id = :id", nativeQuery = true)
    Optional<ReceptionEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM receptions", nativeQuery = true)
    List<ReceptionEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM ReceptionEntity c")
    Page<ReceptionEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM ReceptionEntity c
       WHERE (:number IS NULL OR TRIM(:number) = '' 
              OR LOWER(c.number) LIKE LOWER(CONCAT('%', :number, '%')))
    """)
    Page<ReceptionEntity> search(@Param("number") String number, Pageable pageable);


}