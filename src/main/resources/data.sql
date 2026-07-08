INSERT INTO communes (name, created_at, updated_at, deleted_at)
SELECT 'Punta Arenas', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM communes WHERE name = 'Punta Arenas');
INSERT INTO communes (name, created_at, updated_at, deleted_at)
SELECT 'Puerto Natales', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM communes WHERE name = 'Puerto Natales');
INSERT INTO communes (name, created_at, updated_at, deleted_at)
SELECT 'Porvenir', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM communes WHERE name = 'Porvenir');
INSERT INTO communes (name, created_at, updated_at, deleted_at)
SELECT 'Timaukel', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM communes WHERE name = 'Timaukel');
INSERT INTO communes (name, created_at, updated_at, deleted_at)
SELECT 'San Gregorio', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM communes WHERE name = 'San Gregorio');

INSERT INTO locations (name, created_at, updated_at, deleted_at)
SELECT 'Servicio de Salud Magallanes', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM locations WHERE name = 'Servicio de Salud Magallanes');



INSERT INTO institutions (name, created_at, updated_at, deleted_at)
SELECT 'Carabineros de Chile', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'Carabineros de Chile');
INSERT INTO institutions (name, created_at, updated_at, deleted_at)
SELECT 'Policía de Investigaciones', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'Policía de Investigaciones');
INSERT INTO institutions (name, created_at, updated_at, deleted_at)
SELECT 'PDI', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'PDI');
INSERT INTO institutions (name, created_at, updated_at, deleted_at)
SELECT 'Fiscalía Regional', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'Fiscalía Regional');
INSERT INTO institutions (name, created_at, updated_at, deleted_at)
SELECT 'Servicio de Salud Magallanes', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'Servicio de Salud Magallanes');

INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at)
SELECT 'Comisaría', 1, 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions_types WHERE name = 'Comisaría');
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at)
SELECT 'Brigada de Droga', 1, 2, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions_types WHERE name = 'Brigada de Droga');
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at)
SELECT 'Central de Policía', 1, 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions_types WHERE name = 'Central de Policía');
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at)
SELECT 'Delegación PDI', 1, 3, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions_types WHERE name = 'Delegación PDI');
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at)
SELECT 'Sección de Laboratorio', 1, 5, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM institutions_types WHERE name = 'Sección de Laboratorio');


INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Subteniente', 'Oficial de rango medio', 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Subteniente');
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Sargento', 'Suboficial profesional', 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Sargento');
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Cabo', 'Suboficial de rango inferior', 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Cabo');
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Inspector', 'Oficial de investigación', 2, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Inspector');
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Detective', 'Profesional investigador', 4, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Detective');
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at)
SELECT 'Técnico', 'Personal técnico', 5, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM grades WHERE name = 'Técnico');

INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Bolsa plástica', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Bolsa plástica');
INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Botella de vidrio', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Botella de vidrio');
INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Frasco de vidrio', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Frasco de vidrio');
INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Tubo de ensayo', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Tubo de ensayo');
INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Caja de cartón', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Caja de cartón');
INSERT INTO packagings (name, created_at, updated_at, deleted_at)
SELECT 'Bolsa de sellado', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM packagings WHERE name = 'Bolsa de sellado');

INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Cocaína', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Cocaína');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Marihuana', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Marihuana');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Pasta base', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Pasta base');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Heroína', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Heroína');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Metanfetamina', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Metanfetamina');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'MDMA/Éxtasis', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'MDMA/Éxtasis');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'LSD', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'LSD');
INSERT INTO substances_types (name, created_at, updated_at, deleted_at)
SELECT 'Fentanilo', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM substances_types WHERE name = 'Fentanilo');

INSERT INTO storages_locations (name, created_at, updated_at, deleted_at)
SELECT 'Ubicación 001', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM storages_locations WHERE name = 'Ubicación 001');
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at)
SELECT 'Ubicación 002', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM storages_locations WHERE name = 'Ubicación 002');
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at)
SELECT 'Ubicación 003', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM storages_locations WHERE name = 'Ubicación 003');
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at)
SELECT 'Ubicación 004', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM storages_locations WHERE name = 'Ubicación 004');
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at)
SELECT 'Ubicación 005', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM storages_locations WHERE name = 'Ubicación 005');

INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at)
SELECT 'Destrucción por incineración', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM methods_destructions WHERE name = 'Destrucción por incineración');
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at)
SELECT 'Destrucción química', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM methods_destructions WHERE name = 'Destrucción química');
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at)
SELECT 'Trituración', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM methods_destructions WHERE name = 'Trituración');
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at)
SELECT 'Enterramiento', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM methods_destructions WHERE name = 'Enterramiento');
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at)
SELECT 'Compostaje', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM methods_destructions WHERE name = 'Compostaje');

INSERT INTO destinations (name, created_at, updated_at, deleted_at)
SELECT 'Laboratorio Central', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM destinations WHERE name = 'Laboratorio Central');
INSERT INTO destinations (name, created_at, updated_at, deleted_at)
SELECT 'Instituto de Toxicología', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM destinations WHERE name = 'Instituto de Toxicología');
INSERT INTO destinations (name, created_at, updated_at, deleted_at)
SELECT 'Laboratorio Forense', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM destinations WHERE name = 'Laboratorio Forense');
INSERT INTO destinations (name, created_at, updated_at, deleted_at)
SELECT 'Centro de Análisis Químico', '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM destinations WHERE name = 'Centro de Análisis Químico');


INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at)
SELECT '15.555.555-5', 'Carlos', 'Antonio', 'Martinez', 'Silva', 'carlos.martinez@carabineros.cl', '912555555', 1, 1, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM polices WHERE rut = '15.555.555-5');
INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at)
SELECT '16.666.666-6', 'Roberto', 'Felipe', 'Gutiérrez', 'Morales', 'roberto.gutierrez@pdi.cl', '912666666', 4, 2, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM polices WHERE rut = '16.666.666-6');
INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at)
SELECT '17.777.777-7', 'Patricia', 'Alejandra', 'Villalobos', 'Paez', 'patricia.villalobos@pdi.cl', '912777777', 5, 4, '2025-11-25 10:00:00', '2025-11-25 10:00:00', NULL
WHERE NOT EXISTS (SELECT 1 FROM polices WHERE rut = '17.777.777-7');

