package com.store.swagger;

import com.google.common.collect.ImmutableSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = ImmutableSet.of(
            "application/json"
    );

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.store.controllers"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo())
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Online store REST Api")
                .contact(new Contact("Misha Avizhen", null, "misha33379@mail.ru"))
                .description("Online store")
                .build();
    }
}
