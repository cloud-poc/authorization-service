package org.akj.springboot.authorization.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Value("${swagger.api.title}")
	private String title;

	@Value("${swagger.api.description}")
	private String description;

	@Value("${swagger.api.terms-of-service-url}")
	private String termsOfServiceUrl;

	@Value("${swagger.api.version}")
	private String version;

	@Value("${swagger.api.controller.basepackage}")
	private String basePackage;

	@Value("${swagger.api.contact.name:null}")
	private String name;

	@Value("${swagger.api.contact.url:null}")
	private String url;

	@Value("${swagger.api.contact.email:null}")
	private String email;

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(regex("/**")).build().apiInfo(metaData());

	}

	private ApiInfo metaData() {
		Contact contact = new Contact(name, url, email);
		ApiInfo apiInfo = new ApiInfoBuilder().title(title).description(description)
				.termsOfServiceUrl(termsOfServiceUrl).version(version).contact(contact).build();
		return apiInfo;
	}
}