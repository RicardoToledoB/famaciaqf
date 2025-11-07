package com.farmacia.v1.repository;

import com.farmacia.v1.entity.ReceptionHistoryEntity;
import com.farmacia.v1.entity.ReceptionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionHistoryRepository extends JpaRepository<ReceptionHistoryEntity,Integer> {
    @Query(
            value = "SELECT * FROM receptions_histories c WHERE c.deleted_at IS NOT NULL",
            nativeQuery = true
    )
    List<ReceptionHistoryEntity> findAllDeleted();

    @Query("SELECT ur FROM ReceptionHistoryEntity ur WHERE ur.deletedAt IS NULL")
    List<ReceptionHistoryEntity> findAllActive();

    @Query(value = "SELECT * FROM receptions_histories c WHERE c.id = :id", nativeQuery = true)
    Optional<ReceptionHistoryEntity> findAnyById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM receptions_histories", nativeQuery = true)
    List<ReceptionHistoryEntity> findAllIncludingDeleted();

    @Query("SELECT c FROM ReceptionHistoryEntity c")
    Page<ReceptionHistoryEntity> findAllPaginated(Pageable pageable);

    @Query("""
       SELECT c FROM ReceptionHistoryEntity c
       WHERE (:description IS NULL OR TRIM(:description) = '' 
              OR LOWER(c.description) LIKE LOWER(CONCAT('%', :description, '%')))
    """)
    Page<ReceptionHistoryEntity> search(@Param("description") String name, Pageable pageable);

    List<ReceptionHistoryEntity> findByReception_Id(Integer receptionId);

}