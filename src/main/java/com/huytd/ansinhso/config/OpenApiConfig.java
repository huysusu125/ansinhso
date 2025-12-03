package com.huytd.ansinhso.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class OpenApiConfig {

    @Value("${openapi.dev-url:http://localhost:8080}")
    private String devUrl;

    @Value("${openapi.prod-url:https://api.ansinhso.com}")
    private String prodUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Common")
                .pathsToMatch("/public-api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cmsApi() {
        return GroupedOpenApi.builder()
                .group("Cms")
                .pathsToMatch("/cms-api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi appApi() {
        return GroupedOpenApi.builder()
                .group("App")
                .pathsToMatch("/app-api/**")
                .build();
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Production Server");

        Contact contact = new Contact();
        contact.setEmail("contact@ansinhso.com");
        contact.setName("AnSinhSo Team");
        contact.setUrl("https://www.ansinhso.com");

        License mitLicense = new License()
                .name("Huy td License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("AnSinhSo News Management API")
                .version("1.0.0")
                .contact(contact)
                .description("API documentation for AnSinhSo news management system. " +
                        "This API provides endpoints for managing topics and news articles.")
                .termsOfService("https://www.ansinhso.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
