package com.conny.blog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableWebMvc
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.conny.blog.web"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(
                ApiInfoBuilder()
                    .description("Welcome to Conny Api")
                    .title("Blog API Service")
                    .version("0.0.1-SNAPSHOT")
                    .contact(
                        Contact(
                            "Conny",
                            "https://github.com/mugon-dev",
                            "jmg41490400@gmail.com"
                        )
                    )
                    .build()
            )
    }
}