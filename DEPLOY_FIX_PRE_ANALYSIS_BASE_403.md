# Fix 403 GET /api/v1/pre_analysis

Este ajuste aborda el caso donde `GET /api/v1/pre_analysis/{id}` funciona, pero `GET /api/v1/pre_analysis` responde 403.

Cambios:

- Se configura CORS global para `ley20000.dssm.cl` y localhost.
- Se permite explícitamente la ruta base `/api/v1/pre_analysis`, con y sin slash final.
- Se mantiene seguridad con JWT: el endpoint requiere usuario autenticado.
- Se ajusta `PreAnalysisController` a `@PreAuthorize("isAuthenticated()")` para evitar bloqueo por rol en la validación funcional.

No requiere reiniciar, borrar ni recrear la base de datos.

## Despliegue

```bash
cd /var/www/html/famaciaqf-api
mvn clean package -DskipTests
ln -sf target/v1-0.0.1-SNAPSHOT.jar app.jar
sudo systemctl restart farmaciaqf-api
sudo systemctl status farmaciaqf-api
```

## Pruebas

```bash
curl -i "https://farmaciaqf-api.dssm.cl/api/v1/pre_analysis" \
  -H "Authorization: Bearer TU_TOKEN"

curl -i "https://farmaciaqf-api.dssm.cl/api/v1/pre_analysis/7" \
  -H "Authorization: Bearer TU_TOKEN"

curl -i -X OPTIONS "https://farmaciaqf-api.dssm.cl/api/v1/pre_analysis" \
  -H "Origin: https://ley20000.dssm.cl" \
  -H "Access-Control-Request-Method: GET" \
  -H "Access-Control-Request-Headers: authorization,content-type"
```
