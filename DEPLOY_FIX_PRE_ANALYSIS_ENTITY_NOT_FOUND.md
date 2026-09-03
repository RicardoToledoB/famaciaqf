# Fix GET /api/v1/pre_analysis - EntityNotFoundException en ReceptionEntity

## Problema detectado

El endpoint `GET /api/v1/pre_analysis` no estaba fallando realmente por CORS ni por permisos.
El log muestra que al listar los preanálisis, Hibernate intenta cargar una recepción asociada y no la encuentra:

```text
jakarta.persistence.EntityNotFoundException: Unable to find com.farmacia.v1.entity.ReceptionEntity with id 7
at com.farmacia.v1.service.impl.PreAnalysisServiceImpl.mapToReceptionDTO(...)
at com.farmacia.v1.service.impl.PreAnalysisServiceImpl.listAll(...)
at com.farmacia.v1.controller.PreAnalysisController.getAll(...)
```

Esto ocurre cuando un registro de `pre_analysis` mantiene `reception_id = 7`, pero esa recepción no existe o está eliminada por soft-delete (`deleted_at IS NOT NULL`).

## Qué se corrigió

- Se dejó el mapeo de relaciones de `PreAnalysisServiceImpl` tolerante a relaciones nulas o eliminadas.
- Si una recepción relacionada no existe o está eliminada por soft-delete, la API no cae completa; devuelve el registro con la relación `reception` en `null`.
- Se agregó `/error` como permitido en `SecurityConfig` para evitar que errores internos se enmascaren como 403.
- No se ejecutan scripts de creación de base, no se elimina información y no se reinicia MySQL.

## Despliegue

```bash
cd /var/www/html/famaciaqf-api
mvn clean package -DskipTests
ln -sf target/v1-0.0.1-SNAPSHOT.jar app.jar
sudo systemctl restart farmaciaqf-api
sudo systemctl status farmaciaqf-api
```

## Prueba

```bash
curl -i "https://farmaciaqf-api.dssm.cl/api/v1/pre_analysis" \
  -H "Authorization: Bearer TU_TOKEN"
```

## Diagnóstico opcional de datos huérfanos

Este script solo consulta, no modifica datos:

```bash
mysql -u farmaciaqf_user -p farmaciaqf < sql/diagnostico_preanalysis_reception_huerfanos.sql
```

