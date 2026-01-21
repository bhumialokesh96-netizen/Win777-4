package com.win777.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI win777OpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Win777 Backend API")
                        .description("REST API for Win777 Android Application Backend (Phase 1)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Win777 Team")
                                .email("support@win777.com")));
    }
}
