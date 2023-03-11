package com.ya.bosssave.config;

import com.ya.bosssave.util.jwtTokenValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalRequestParameters(
                        Collections.singletonList(new RequestParameterBuilder()
                                .name("Authorization")
                                .description(" token 信息 ")
                                .in(ParameterType.HEADER)
                                .required(false)
                                .build()));

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("葉尚孟", "", "ya666workhard@gmail.com");

        return new ApiInfo(
                "Boss面試",
                "api接口",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());


    }

}
