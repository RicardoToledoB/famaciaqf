package com.farmacia.v1.security.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI (Swagger UI)
 * -------------------------------------
 * Esta clase define los servidores disponibles para probar la API
 * en diferentes entornos (por ejemplo: local y producción).
 *
 * Una vez que arranque la aplicación, Swagger UI mostrará un selector
 * donde podrás cambiar entre "Local" y "Producción Railway".
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local"),
                        new Server()
                                .url("https://famaciaqf-production.up.railway.app")
                                .description("Servidor Producción (Railway)")
                ));
    }
}