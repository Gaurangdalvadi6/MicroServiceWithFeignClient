package com.gaurang.microservice.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI orderServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Order Service API")
                        .description("This is the Rest API for Order Service")
                        .version("v.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Order service wiki Documentation")
                        .url("https://order-service-dummy-url.com/docs"));
    }
}