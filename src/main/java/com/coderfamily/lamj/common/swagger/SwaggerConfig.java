package com.coderfamily.lamj.common.swagger;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ZhangDL
 * @date 2018/1/25 14:22
 */
@Configurable
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/").apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("ZhangDL", "", "15156888276@139.com");
        return new ApiInfo("Lamj接口文档", "", "v1.0.0", "", contact, "", "", new ArrayList<>());
    }
}
