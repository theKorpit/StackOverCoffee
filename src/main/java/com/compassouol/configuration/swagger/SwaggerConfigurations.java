package com.compassouol.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	@Bean
    public Docket StackOverCoffee() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.compassouol.controller"))              
          .paths(PathSelectors.ant("/**"))                          
          .build()
          .apiInfo(apiInfo());
    }
	private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title ("API Biblioteca Steam")
                .description ("Essa é a API Biblioteca Steam")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("0.5.0")
                .build();

        return apiInfo;
    }
}
