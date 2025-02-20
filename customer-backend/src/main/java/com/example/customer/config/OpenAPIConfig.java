package com.example.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("info@example.com");
        contact.setName("API Support");
        contact.setUrl("https://www.example.com");

        License mitLicense = new License().name("MIT License")
                                        .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Customer Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for managing customers.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(Arrays.asList(devServer));
    }
}
