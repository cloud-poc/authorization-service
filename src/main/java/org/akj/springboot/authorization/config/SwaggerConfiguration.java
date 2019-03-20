package org.akj.springboot.authorization.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean(name = "Authorization APIs")
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.akj"))
				.paths(regex("/*.*")).build().apiInfo(metaData());

	}

	@SuppressWarnings("rawtypes")
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Security Service", "Authorization", "1.0",
				"This is Sample project for authorization using springboot and oauth2 technologies, also provide flexibility for client app registration",
				new Contact("Jamie Zhang", null, "akjamie.zhang@outlook.com"), null, null, new ArrayList());
		return apiInfo;
	}
}