# Fix 403 en pre_analysis / Ley 20000

Este ajuste no reinicia ni recrea la base de datos.

Cambios realizados:

- Se permitió acceso a controladores para roles `ADMIN` y `ADMINISTRATIVO`.
- Se permitió explícitamente `OPTIONS /**` para evitar problemas de preflight CORS.
- Se agregaron los orígenes `http://ley20000.dssm.cl` y `https://ley20000.dssm.cl` en CORS.

Despliegue:

```bash
cd /var/www/html/famaciaqf-api
git pull
mvn clean package -DskipTests
ln -sf target/v1-0.0.1-SNAPSHOT.jar app.jar
sudo systemctl restart farmaciaqf-api
sudo systemctl status farmaciaqf-api
```

Prueba:

```bash
curl -i https://farmaciaqf-api.dssm.cl/api/v1/pre_analysis \
  -H "Authorization: Bearer TU_TOKEN"
```

Si continúa 403, revisar que el usuario tenga un rol activo en `users_roles`.
