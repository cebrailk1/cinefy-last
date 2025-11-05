package org.example.cinefylast.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cinefyOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinefy API")
                        .description("REST API documentation for Cinefy ticketing services")
                        .version("v1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/cebrailk1/cinefy-last"));
    }
}