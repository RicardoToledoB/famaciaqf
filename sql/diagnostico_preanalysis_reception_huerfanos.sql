-- Diagnóstico seguro: NO modifica datos.
-- Busca preanálisis activos que apuntan a recepciones inexistentes o eliminadas por soft-delete.
SELECT
    pa.id AS pre_analysis_id,
    pa.reception_id,
    r.id AS reception_exists,
    r.deleted_at AS reception_deleted_at
FROM pre_analysis pa
LEFT JOIN receptions r ON r.id = pa.reception_id
WHERE pa.deleted_at IS NULL
  AND pa.reception_id IS NOT NULL
  AND (r.id IS NULL OR r.deleted_at IS NOT NULL)
ORDER BY pa.id;
