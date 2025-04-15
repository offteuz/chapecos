package br.com.fiap.chapecos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI chapecos() {
        return new OpenAPI().info(
                new Info().version("v.0.1")
                        .title("Chapecos API")
                        .description("API criada para gerenciamento de pedidos, usuários e clientes do restaurante Chapecos.")
                        .license(new License().name("Chapecos").url("https://github.com/offteuz/"))
        );
    }
}
