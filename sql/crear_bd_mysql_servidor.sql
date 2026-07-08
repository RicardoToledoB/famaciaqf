CREATE DATABASE IF NOT EXISTS farmaciaqf
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'farmaciaqf_user'@'localhost'
IDENTIFIED BY 'CAMBIAR_CLAVE_MYSQL_SERVIDOR';

GRANT ALL PRIVILEGES ON farmaciaqf.*
TO 'farmaciaqf_user'@'localhost';

FLUSH PRIVILEGES;
