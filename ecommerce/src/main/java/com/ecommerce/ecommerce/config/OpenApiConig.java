package com.ecommerce.ecommerce.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConig {
    @Bean
    public OpenAPI myOpenAPI(){
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Server url in local environment");

        Contact contact = new Contact();
        contact.setEmail("test@gmail.com");
        contact.setName("Ecommerce");

        License license = new License().name("MIT License").url("https://test.com");

        Info info = new Info().title("Ecommerce").version("1.0").contact(contact).description("Api for ecommerce").termsOfService("https://www.test.com/terms").license(license);
        SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");

        return new OpenAPI().components(new Components().addSecuritySchemes("Bearer Authentication", securityScheme)).info(info).servers(List.of(localServer));
    }
}
