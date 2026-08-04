# Cambio MicroAnalysis - getByAnalysisId

Se agregó el endpoint:

GET /api/v1/microanalysis/getByAnalysisId/{analysis_id}

Ejemplo productivo:

https://farmaciaqf-api.dssm.cl/api/v1/microanalysis/getByAnalysisId/1

## Importante sobre base de datos

Este cambio NO requiere reiniciar, borrar, recrear ni ejecutar scripts sobre la base de datos.
No ejecutar:

mysql -u root -p < sql/crear_bd_mysql_local.sql
mysql -u root -p < sql/crear_bd_mysql_servidor.sql

El cambio es solo de código Java: controller, service y repository.

## Despliegue sugerido

cd /var/www/html/famaciaqf-api
git pull
mvn clean package -DskipTests
ln -sf target/v1-0.0.1-SNAPSHOT.jar app.jar
sudo systemctl restart farmaciaqf-api
sudo systemctl status farmaciaqf-api

## Prueba

curl -i "https://farmaciaqf-api.dssm.cl/api/v1/microanalysis/getByAnalysisId/1" \
  -H "Authorization: Bearer TU_TOKEN"

Respuestas esperadas:
- 200 OK: endpoint existe y respondió.
- 401/403: endpoint existe, falta token o rol/permisos.
- []: existe y no hay microanálisis para ese analysis_id.
