INSERT INTO communes (name, created_at, updated_at, deleted_at) VALUES ('Punta Arenas',     TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO communes (name, created_at, updated_at, deleted_at) VALUES ('Puerto Natales',   TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO communes (name, created_at, updated_at, deleted_at) VALUES ('Porvenir',         TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO communes (name, created_at, updated_at, deleted_at) VALUES ('Timaukel',         TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO communes (name, created_at, updated_at, deleted_at) VALUES ('San Gregorio',     TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO locations (name, created_at, updated_at, deleted_at) VALUES ('Servicio de Salud Magallanes',     TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);



INSERT INTO institutions (name, created_at, updated_at, deleted_at) VALUES ('Carabineros de Chile',         TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions (name, created_at, updated_at, deleted_at) VALUES ('Policía de Investigaciones',   TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions (name, created_at, updated_at, deleted_at) VALUES ('PDI',                          TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions (name, created_at, updated_at, deleted_at) VALUES ('Fiscalía Regional',            TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions (name, created_at, updated_at, deleted_at) VALUES ('Servicio de Salud Magallanes', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at) VALUES ('Comisaría',              1, 1, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at) VALUES ('Brigada de Droga',       1, 2, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at) VALUES ('Central de Policía',     1, 1, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at) VALUES ('Delegación PDI',         1, 3, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO institutions_types (name, commune_id, institution_id, created_at, updated_at, deleted_at) VALUES ('Sección de Laboratorio', 1, 5, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);


INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Subteniente', 'Oficial de rango medio',           1, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Sargento',    'Suboficial profesional',          1, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Cabo',        'Suboficial de rango inferior',    1, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Inspector',   'Oficial de investigación',        2, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Detective',   'Profesional investigador',        4, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO grades (name, description, institution_type_id, created_at, updated_at, deleted_at) VALUES ('Técnico',     'Personal técnico',                5, TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Bolsa plástica',    TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Botella de vidrio', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Frasco de vidrio',  TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Tubo de ensayo',    TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Caja de cartón',    TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO packagings (name, created_at, updated_at, deleted_at) VALUES ('Bolsa de sellado',  TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Cocaína',         TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Marihuana',       TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Pasta base',      TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Heroína',         TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Metanfetamina',   TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('MDMA/Éxtasis',    TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('LSD',             TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO substances_types (name, created_at, updated_at, deleted_at) VALUES ('Fentanilo',       TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO storages_locations (name, created_at, updated_at, deleted_at) VALUES ('Ubicación 001', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at) VALUES ('Ubicación 002', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at) VALUES ('Ubicación 003', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at) VALUES ('Ubicación 004', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO storages_locations (name, created_at, updated_at, deleted_at) VALUES ('Ubicación 005', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at) VALUES ('Destrucción por incineración', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at) VALUES ('Destrucción química',           TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at) VALUES ('Trituración',                   TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at) VALUES ('Enterramiento',                 TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO methods_destructions (name, created_at, updated_at, deleted_at) VALUES ('Compostaje',                    TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);

INSERT INTO destinations (name, created_at, updated_at, deleted_at) VALUES ('Laboratorio Central',        TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO destinations (name, created_at, updated_at, deleted_at) VALUES ('Instituto de Toxicología',   TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO destinations (name, created_at, updated_at, deleted_at) VALUES ('Laboratorio Forense',        TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);
INSERT INTO destinations (name, created_at, updated_at, deleted_at) VALUES ('Centro de Análisis Químico', TIMESTAMP '2025-11-25 10:00:00', TIMESTAMP '2025-11-25 10:00:00', NULL);


INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at) VALUES ('15.555.555-5','Carlos','Antonio','Martinez','Silva','carlos.martinez@carabineros.cl','912555555',1,1,TIMESTAMP '2025-11-25 10:00:00',TIMESTAMP '2025-11-25 10:00:00',NULL);
INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at) VALUES ('16.666.666-6','Roberto','Felipe','Gutiérrez','Morales','roberto.gutierrez@pdi.cl','912666666',4,2,TIMESTAMP '2025-11-25 10:00:00',TIMESTAMP '2025-11-25 10:00:00',NULL);
INSERT INTO polices (rut, first_name, second_name, first_last_name, second_last_name, email, cellphone, grade_id, institution_type_id, created_at, updated_at, deleted_at) VALUES ('17.777.777-7','Patricia','Alejandra','Villalobos','Paez','patricia.villalobos@pdi.cl','912777777',5,4,TIMESTAMP '2025-11-25 10:00:00',TIMESTAMP '2025-11-25 10:00:00',NULL);

