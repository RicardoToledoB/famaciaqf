CREATE DATABASE IF NOT EXISTS farmaciaqf
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Usuario sugerido para ambiente local/servidor. Cambiar la clave antes de usar en producción.
CREATE USER IF NOT EXISTS 'farmaciaqf_user'@'localhost' IDENTIFIED BY 'Cambiar_Clave_Segura_2026';
GRANT ALL PRIVILEGES ON farmaciaqf.* TO 'farmaciaqf_user'@'localhost';
FLUSH PRIVILEGES;
