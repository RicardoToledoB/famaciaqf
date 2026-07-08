# Configuración MySQL - farmaciaqf-api

El proyecto quedó configurado para MySQL por defecto.

## 1. Crear base de datos local

Puedes crearla manualmente con:

```sql
CREATE DATABASE IF NOT EXISTS farmaciaqf
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

También se incluye el archivo `mysql-setup.sql` con una base y usuario sugerido.

## 2. Configuración local por defecto

El archivo `src/main/resources/application.properties` queda apuntando a:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/farmaciaqf?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=
```

Si tu MySQL local tiene clave, puedes ejecutar con variables de entorno:

```bash
export DB_URL="jdbc:mysql://localhost:3306/farmaciaqf?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago&characterEncoding=UTF-8"
export DB_USERNAME="root"
export DB_PASSWORD="TU_CLAVE_MYSQL"
./mvnw spring-boot:run
```

## 3. Datos iniciales

`data.sql` fue ajustado para MySQL y quedó idempotente, por lo que no debería duplicar los catálogos al reiniciar.

Además, `DataSeeder.java` ahora crea los usuarios y roles solo si no existen.

Usuarios iniciales:

```text
admin / Secret123$
operador / Secret123$
```

## 4. Producción / servidor

Para el servidor `farmaciaqf-api.dssm.cl`, se agregó el perfil:

```text
src/main/resources/application-prod.properties
```

Ejemplo de ejecución:

```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_URL="jdbc:mysql://localhost:3306/farmaciaqf?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago&characterEncoding=UTF-8"
export DB_USERNAME="farmaciaqf_user"
export DB_PASSWORD="CLAVE_SEGURA"
export APP_JWT_SECRET="CAMBIAR_POR_UN_SECRETO_LARGO_Y_SEGURO_DE_256_BITS"
export PORT="8080"
java -jar target/v1-0.0.1-SNAPSHOT.jar
```

En producción `spring.sql.init.mode` queda en `never` por defecto para no reinsertar datos semilla en cada despliegue.
