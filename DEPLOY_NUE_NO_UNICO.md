# Cambio: NUE no único en substances

Este ajuste permite que el campo `nue` se pueda repetir al crear sustancias mediante:

`POST /api/v1/substances`

## Qué se modificó

- `SubstanceEntity.java`: se deja el campo `nue` sin restricción de unicidad a nivel de código.
- `sql/permitir_nue_duplicado_substances.sql`: script seguro para eliminar, si existe, cualquier índice `UNIQUE` sobre `substances.nue`.

## Importante

Este cambio NO requiere reiniciar, borrar ni recrear la base de datos.

No ejecutar:

```bash
mysql -u root -p < sql/crear_bd_mysql_local.sql
mysql -u root -p < sql/crear_bd_mysql_servidor.sql
```

## Aplicación en producción

Desde el servidor:

```bash
cd /var/www/html/famaciaqf-api
```

Aplicar solo el script de ajuste del índice:

```bash
mysql -u farmaciaqf_user -p farmaciaqf < sql/permitir_nue_duplicado_substances.sql
```

Luego compilar y reiniciar el servicio:

```bash
mvn clean package -DskipTests
ln -sf target/v1-0.0.1-SNAPSHOT.jar app.jar
sudo systemctl restart farmaciaqf-api
sudo systemctl status farmaciaqf-api
```

## Validación

Probar crear dos sustancias con el mismo `nue`:

```bash
curl -i -X POST "https://farmaciaqf-api.dssm.cl/api/v1/substances" \
  -H "Authorization: Bearer TU_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nue": "NUE-PRUEBA-REPETIDA",
    "description": "Prueba sustancia 1",
    "weight": "1",
    "nsubstance": "N° 1",
    "state": "ACTIVO"
  }'
```

Repetir el POST con el mismo `nue` y otro `nsubstance`. Si responde 200/201 y crea el registro, el ajuste quedó correcto.
