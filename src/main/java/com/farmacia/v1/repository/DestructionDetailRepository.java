package com.farmacia.v1.repository;

import com.farmacia.v1.entity.DestructionDetailEntity;
import com.farmacia.v1.entity.DestructionDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestructionDetailRepository extends JpaRepository<DestructionDetailEntity,Integer> {
    @Query(
            value = "SELECT * FROM destructions_details c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<DestructionDetailEntity> findAllDeleted();

    @Query("SELECT ur FROM DestructionDetailEntity ur WHERE ur.deletedAt IS NULL")
    List<DestructionDetailEntity> findAllActive();

    @Query(value = "SELECT * FROM destructions_details c WHERE c.id = :id", nativeQuery = true)
    Optional<DestructionDetailEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM destructions_details", nativeQuery = true)
    List<DestructionDetailEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM DestructionDetailEntity c")
    Page<DestructionDetailEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM DestructionDetailEntity c
       WHERE (:state IS NULL OR TRIM(:state) = '' 
              OR LOWER(c.state) LIKE LOWER(CONCAT('%', :state, '%')))
    """)
    Page<DestructionDetailEntity> search(@Param("state") String state, Pageable pageable);

    // Activos (respeta @Where deleted_at IS NULL)
    List<DestructionDetailEntity> findByDestructionHeader_Id(Integer destructionHeaderId);

}
