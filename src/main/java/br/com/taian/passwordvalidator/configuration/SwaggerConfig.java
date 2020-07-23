package br.com.taian.passwordvalidator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.security.Principal;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("password-validator-1.0")
                .ignoredParameterTypes(Principal.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.taian.passwordvalidator.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Password Validator API").description("Documentation API v1.0").build());
    }

}
