package com.backend.chatop.config.Swagger;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@RestController
@OpenAPIDefinition(info =
@Info(
        title = "Rentals",
        version = "1.0",
        description = "Documentation for Rentals API Application",
        license = @License(name = "Version 0.1", url = "http://localhost:3001/api"),
        contact = @Contact( name = "Osiris", email = "kontchou05@gmail.com")
)
)
public class APIDocConfig {
}
