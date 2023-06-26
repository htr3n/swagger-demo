package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Swagger Demo",
            version = "1.0.0",
            description = "This is a demo of Swagger OpenAPI spec for RESTEasy endpoints",
            license = @License(name = "Apache 2.0", url = "https://example.org"),
            contact =
                @Contact(
                    url = "https://example.org",
                    name = "Administrator",
                    email = "admin@example.org")))
@ApplicationPath("/api")
public class MainApp extends Application {}
