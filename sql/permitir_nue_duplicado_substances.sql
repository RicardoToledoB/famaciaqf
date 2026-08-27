-- Permite que el campo NUE se repita en la tabla substances.
-- Este script NO borra datos, NO reinicia la base de datos y NO recrea tablas.
-- Solo elimina índices UNIQUE existentes sobre la columna substances.nue, si los hubiera.

USE farmaciaqf;

DELIMITER //

DROP PROCEDURE IF EXISTS drop_unique_indexes_on_substances_nue//

CREATE PROCEDURE drop_unique_indexes_on_substances_nue()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE idx_name VARCHAR(128);

    DECLARE cur CURSOR FOR
        SELECT DISTINCT s.INDEX_NAME
        FROM INFORMATION_SCHEMA.STATISTICS s
        WHERE s.TABLE_SCHEMA = DATABASE()
          AND s.TABLE_NAME = 'substances'
          AND s.COLUMN_NAME = 'nue'
          AND s.NON_UNIQUE = 0
          AND s.INDEX_NAME <> 'PRIMARY';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO idx_name;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SET @drop_sql = CONCAT('ALTER TABLE `substances` DROP INDEX `', REPLACE(idx_name, '`', '``'), '`');
        PREPARE stmt FROM @drop_sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END LOOP;

    CLOSE cur;
END//

DELIMITER ;

CALL drop_unique_indexes_on_substances_nue();
DROP PROCEDURE IF EXISTS drop_unique_indexes_on_substances_nue;

-- Validación: ya no debería aparecer ningún índice UNIQUE para la columna nue.
SELECT
    TABLE_SCHEMA,
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    NON_UNIQUE
FROM INFORMATION_SCHEMA.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'substances'
  AND COLUMN_NAME = 'nue'
ORDER BY INDEX_NAME, SEQ_IN_INDEX;
