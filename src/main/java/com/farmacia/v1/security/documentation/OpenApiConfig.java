package com.farmacia.v1.security.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI (Swagger UI) con autenticación JWT.
 *
 * 🔹 Muestra selector de entornos (Local / Railway)
 * 🔹 Agrega "candadito" para autenticarse con Bearer Token (JWT)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Definición del esquema de seguridad (JWT)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        // Requisito de seguridad (aplica el esquema a todos los endpoints)
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                // Información general del API
                .info(new Info()
                        .title("API FarmaciaQF")
                        .version("v1.0")
                        .description("Documentación de endpoints con autenticación JWT."))
                // Servidores disponibles
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor Local"),
                        new Server().url("https://famaciaqf-production.up.railway.app").description("Producción Railway")
                ))
                // Agregar esquema y requisito de seguridad global
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", securityScheme));
    }
}