package com.farmacia.v1.repository;

import com.farmacia.v1.entity.SubstanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubstanceRepository extends JpaRepository<SubstanceEntity,Integer> {
    @Query(
            value = "SELECT * FROM substances c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<SubstanceEntity> findAllDeleted();

    @Query("SELECT ur FROM SubstanceEntity ur WHERE ur.deletedAt IS NULL")
    List<SubstanceEntity> findAllActive();

    @Query(value = "SELECT * FROM substances c WHERE c.id = :id", nativeQuery = true)
    Optional<SubstanceEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM substances", nativeQuery = true)
    List<SubstanceEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM SubstanceEntity c")
    Page<SubstanceEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM SubstanceEntity c
       WHERE (:nue IS NULL OR TRIM(:nue) = '' 
              OR LOWER(c.nue) LIKE LOWER(CONCAT('%', :nue, '%')))
    """)
    Page<SubstanceEntity> search(@Param("nue") String nue, Pageable pageable);

    @Query("""
       SELECT c FROM SubstanceEntity c
       WHERE (:state IS NULL OR TRIM(:state) = '' 
              OR LOWER(c.state) LIKE LOWER(CONCAT('%', :state, '%')))
    """)
    Page<SubstanceEntity> searchByState(@Param("state") String state, Pageable pageable);

    @Query("SELECT s FROM SubstanceEntity s WHERE s.reception.id = :receptionId")
    List<SubstanceEntity> findAllByReceptionId(@Param("receptionId") Integer receptionId);
}
