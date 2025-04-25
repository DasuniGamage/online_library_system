package com.rootcode.online_library_system.config.openApiConfig;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Online Library System API",
                version = "1.0",
                description = "API documentation for the online library system"
        )
)
public class OpenApiConfig {
}
